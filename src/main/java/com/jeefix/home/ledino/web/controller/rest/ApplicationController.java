package com.jeefix.home.ledino.web.controller.rest;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeefix.home.ledino.common.constant.ApplicationPropertyExpression;
import com.jeefix.home.ledino.common.model.web.ApplicationConfiguration;
import com.jeefix.home.ledino.logic.arduino.ArduinoService;

@Controller
@RequestMapping("/rest")
public class ApplicationController {
    
    private static final Logger log = Logger.getLogger(ApplicationConfiguration.class);
    
    @Autowired
    private ArduinoService arduinoService;
    @Value(ApplicationPropertyExpression.APP_WEBSOCKET_CHANNEL_STATE_CHANGE_URL)
    private String arduinoStateBroadcastUrl;
    
    @RequestMapping("/application/configuration")
    @ResponseBody
    public ApplicationConfiguration getConfiguration(HttpServletRequest request) {
        log.info(String.format("Received request to send application configuration"));
        ApplicationConfiguration config = new ApplicationConfiguration();
        
        // extracts URL without URI (request.getRemoteHost() didn't work well on relative links)
        config.setApplicationHost(request.getRequestURL().substring(request.getProtocol().length() - 1,
            request.getRequestURL().length() - request.getRequestURI().length()));
        config.setArduinoState(arduinoService.getArduinoState());
        config.setArduinoStateBroadcastUrl(arduinoStateBroadcastUrl);
        return config;
    }
}
