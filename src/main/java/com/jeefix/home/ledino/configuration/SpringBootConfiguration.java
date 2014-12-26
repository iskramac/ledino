package com.jeefix.home.ledino.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.jeefix.home"})
@EnableAutoConfiguration
public class SpringBootConfiguration {

    private static final Logger log = LoggerFactory.getLogger(SpringBootConfiguration.class);

    public static void main(String[] args) throws Exception {
        log.debug("Starting ledino context...");
        SpringApplication.run(SpringBootConfiguration.class, args);
        log.debug("Leding context has been destroyed");
    }
}
