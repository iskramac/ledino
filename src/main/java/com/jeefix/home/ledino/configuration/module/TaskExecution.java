package com.jeefix.home.ledino.configuration.module;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Created by mais on 2014-12-18.
 */
@Configuration
@EnableScheduling
@EnableAsync
public class TaskExecution {

    @Bean
    public TaskExecutor taskExecutor() {
       return new ThreadPoolTaskExecutor();

    }
}
