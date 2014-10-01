package com.jeefix.home.ledino.web.configuration;

import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import com.jeefix.home.ledino.logic.demo.AnimationService;
import com.jeefix.home.ledino.logic.demo.animation.FadeInAnimation;
import com.jeefix.home.ledino.logic.demo.animation.FadeOutAnimation;

@Component
public class SpringAfterStartupConfiguration implements ApplicationListener<ContextRefreshedEvent> {
    
    private static final Logger log = Logger.getLogger(AsynchronousServerSocketChannel.class);
    @Autowired
    private AnimationService animationService;
    
    @Autowired
    private TaskScheduler scheduler;
    
    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        log.info("Settinging ledino auto ON at 18 P.M.");
        ScheduledFuture<?> autoOnShedule = scheduler.schedule(new TurnLedinoOn(), new CronTrigger("0 0 18 1/1 * ?"));
        log.info(String.format("Ledino will auto ON in %s minutes", autoOnShedule.getDelay(TimeUnit.MINUTES)));
        log.info("Settinging ledino auto OFF at 0 A.M.");
        ScheduledFuture<?> autoOffShedule = scheduler.schedule(new TurnLedinoOff(), new CronTrigger("0 0 0 1/1 * ?"));
        log.info(String.format("Ledino will auto OFF in %s minutes", autoOffShedule.getDelay(TimeUnit.MINUTES)));
        
        if (autoOnShedule.getDelay(TimeUnit.MINUTES) > autoOffShedule.getDelay(TimeUnit.MINUTES)) {
            log.info(String.format("Ledino should be currently turned On!"));
            new TurnLedinoOn().run();
        }
    }
    
    private class TurnLedinoOn implements Runnable {
        
        @Override
        public void run() {
            SpringAfterStartupConfiguration.this.animationService.startAnimation(FadeInAnimation.ANIMATION_NAME);
        }
    }
    
    private class TurnLedinoOff implements Runnable {
        
        @Override
        public void run() {
            SpringAfterStartupConfiguration.this.animationService.startAnimation(FadeOutAnimation.ANIMATION_NAME);
        }
    }
    
}
