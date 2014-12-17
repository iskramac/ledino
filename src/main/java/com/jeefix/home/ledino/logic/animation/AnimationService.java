package com.jeefix.home.ledino.logic.animation;

import com.jeefix.home.ledino.common.exception.LedinoRuntimeException;
import com.jeefix.home.ledino.logic.animation.type.AnimationBase;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * This class is reponsible for handling animations
 */
@Service
public class AnimationService {

    private static final Logger log = Logger.getLogger(AnimationService.class);

    private HashMap<String, AnimationBase> animationsMap = new HashMap<String, AnimationBase>();

    @Autowired
    private AnimationRunableWrapper animationRunableWrapper;

    @Autowired
    @Qualifier("taskExecutor")
    private TaskExecutor taskExecutor;

    @Autowired
    private List<AnimationBase> animations;

    @PostConstruct
    public void init() {
        for (AnimationBase animationBase : animations) {
            String animationName = animationBase.getName().toLowerCase();
            animationsMap.put(animationName, animationBase);
            log.info(String.format("Resolved type: %s", animationName));
        }
    }

    /**
     * Starts type, or repleace currently running
     *
     * @param name       of animation
     * @param properties of animation
     */
    public void startAnimation(String name, Map<String, String> properties) {
        name = name.toLowerCase();
        AnimationBase animation = animationsMap.get(name);
        if (animation == null) {
            throw new LedinoRuntimeException(String.format("Animation '%s' has not been found", name));
        }
        animation.setProperties(properties);
        animationRunableWrapper.setAnimation(animation);
        if (AnimationStatus.IN_PROGESS.equals(animationRunableWrapper.getStatus()) == false) {//in case of previous type has already finished
            taskExecutor.execute(animationRunableWrapper);
        }
    }

    /**
     * Stops current type, if is in progress
     */
    public void stopCurrentAnimation() {
        animationRunableWrapper.setStatus(AnimationStatus.SHOUD_BE_TERMINATED);
    }

}
