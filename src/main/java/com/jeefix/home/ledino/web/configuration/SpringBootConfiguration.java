package com.jeefix.home.ledino.web.configuration;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@ComponentScan(basePackages = {"com.jeefix.home"})
@EnableAutoConfiguration
@EnableScheduling
@EnableAsync
public class SpringBootConfiguration {
    
    private static final Logger log = Logger.getLogger(SpringBootConfiguration.class);
    
    public static void main(String[] args) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug(String.format("Starting Ledino"));
        }
        
        SpringApplication.run(SpringBootConfiguration.class, args);
    }
    
    @Bean(name = "taskExecutor")
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(50);
        return executor;
    }
    
}
