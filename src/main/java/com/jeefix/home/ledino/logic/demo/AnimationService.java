package com.jeefix.home.ledino.logic.demo;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import com.jeefix.home.ledino.common.exception.LedinoRuntimeException;
import com.jeefix.home.ledino.common.model.demo.AnimationStatus;
import com.jeefix.home.ledino.logic.arduino.ArduinoService;
import com.jeefix.home.ledino.logic.demo.animation.AnimationBase;

@Service
public class AnimationService {
    
    private static final Logger log = Logger.getLogger(AnimationService.class);
    @Autowired
    private ArduinoService service;
    
    @Autowired
    @Qualifier("taskExecutor")
    private TaskExecutor taskExecutor;
    
    @Autowired
    private List<AnimationBase> demos;
    
    private HashMap<String, AnimationBase> animationsMap;
    
    private Lock lock = new ReentrantLock();
    
    @PostConstruct
    public void init() {
        animationsMap = new HashMap<String, AnimationBase>();
        for (AnimationBase animationBase : demos) {
            animationsMap.put(animationBase.getName().toLowerCase(), animationBase);
            log.info(String.format("Resolved animation: %s", animationBase.getName().toLowerCase()));
        }
        
    }
    
    public void startAnimation(String name) {
        lock.lock();
        name = name.toLowerCase();
        try {
            if (animationsMap.containsKey(name) == false) {
                throw new LedinoRuntimeException(String.format("Demo '%s' has not been found", name));
            }
            AnimationBase animation = animationsMap.get(name);
            if (animation.getStatus() != AnimationStatus.STARTED) {
                animation.setStatus(AnimationStatus.STARTED);
                taskExecutor.execute(animation);
            } else {
                log.warn(String.format("Current animation '%s' state '%s' does not allow exectution", animation.getName(),
                    animation.getStatus()));
            }
            
        } finally {
            lock.unlock();
        }
        
    }
    
    public void stopAllAnimations() {
        for (AnimationBase animationBase : animationsMap.values()) {
            animationBase.setStatus(AnimationStatus.SHOUD_BE_TERMINATED);
        }
        
    }
}
