package com.jeefix.home.ledino.configuration;

import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import com.jeefix.home.ledino.logic.animation.AnimationService;
import com.jeefix.home.ledino.logic.animation.type.FadeToAnimation;

@Component
public class SpringAfterStartupConfiguration implements ApplicationListener<ContextRefreshedEvent> {
    
    private static final Logger log = Logger.getLogger(AsynchronousServerSocketChannel.class);
    @Autowired
    private AnimationService animationService;
    
    @Autowired
    @Qualifier("taskScheduler")
    private TaskScheduler scheduler;
    
    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        log.info("Settinging ledino auto ON at 16 P.M.");
        ScheduledFuture<?> autoOnShedule = scheduler.schedule(new TurnLedinoOn(), new CronTrigger("0 0 16 1/1 * ?"));
        log.info(String.format("Ledino will auto ON in %s minutes", autoOnShedule.getDelay(TimeUnit.MINUTES)));
        ScheduledFuture<?> autoOffSheduleOne =
            scheduler.schedule(new TurnLedinoOffStageOne(), new CronTrigger("0 0 22 1/1 * ?"));
        ScheduledFuture<?> autoOffSheduleTwo =
            scheduler.schedule(new TurnLedinoOffStageTwo(), new CronTrigger("0 0 23 1/1 * ?"));
        ScheduledFuture<?> autoOffSheduleThree =
            scheduler.schedule(new TurnLedinoOffStageThree(), new CronTrigger("0 0 0 1/1 * ?"));
        
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if (hour == 22) {
            
            new TurnLedinoOffStageOne().run();
        } else if (hour == 23) {
            new TurnLedinoOffStageTwo().run();
        } else if (hour < 16) {
            new TurnLedinoOffStageThree().run();
        } else {
            new TurnLedinoOn().run();
        }
        
        /*
         * if (autoOnShedule.getDelay(TimeUnit.MINUTES) >
         * autoOffSheduleThree.getDelay(TimeUnit.MINUTES)) {
         * log.info(String.format("Ledino should be currently turned On!"));
         * new TurnLedinoOn().run();
         * }
         */
    }
    
    private class TurnLedinoOn implements Runnable {
        
        @Override
        public void run() {
            Map<String, String> props = new HashMap<String, String>();
            props.put(FadeToAnimation.PROP_BLUE_VAL, "255");
            props.put(FadeToAnimation.PROP_GREEN_VAL, "255");
            props.put(FadeToAnimation.PROP_RED_VAL, "255");
            SpringAfterStartupConfiguration.this.animationService.startAnimation(FadeToAnimation.ANIMATION_NAME, props);
        }
    }
    
    private class TurnLedinoOffStageOne implements Runnable {
        
        @Override
        public void run() {
            Map<String, String> props = new HashMap<String, String>();
            props.put(FadeToAnimation.PROP_BLUE_VAL, "255");
            props.put(FadeToAnimation.PROP_GREEN_VAL, "128");
            props.put(FadeToAnimation.PROP_RED_VAL, "0");
            SpringAfterStartupConfiguration.this.animationService.startAnimation(FadeToAnimation.ANIMATION_NAME, props);
        }
    }
    
    private class TurnLedinoOffStageTwo implements Runnable {
        
        @Override
        public void run() {
            Map<String, String> props = new HashMap<String, String>();
            props.put(FadeToAnimation.PROP_BLUE_VAL, "255");
            props.put(FadeToAnimation.PROP_GREEN_VAL, "0");
            props.put(FadeToAnimation.PROP_RED_VAL, "0");
            SpringAfterStartupConfiguration.this.animationService.startAnimation(FadeToAnimation.ANIMATION_NAME, props);
        }
    }
    
    private class TurnLedinoOffStageThree implements Runnable {
        
        @Override
        public void run() {
            Map<String, String> props = new HashMap<String, String>();
            props.put(FadeToAnimation.PROP_BLUE_VAL, "0");
            props.put(FadeToAnimation.PROP_GREEN_VAL, "0");
            props.put(FadeToAnimation.PROP_RED_VAL, "0");
            SpringAfterStartupConfiguration.this.animationService.startAnimation(FadeToAnimation.ANIMATION_NAME, props);
        }
    }
    
}
