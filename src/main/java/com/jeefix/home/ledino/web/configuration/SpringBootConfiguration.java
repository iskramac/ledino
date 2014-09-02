package com.jeefix.home.ledino.web.configuration;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.jeefix.home"})
@EnableAutoConfiguration
public class SpringBootConfiguration {
  private static final Logger log = Logger.getLogger(SpringBootConfiguration.class);

  public static void main(String[] args) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(String.format("Starting Ledino"));
    }

    SpringApplication.run(SpringBootConfiguration.class, args);
  }

}
