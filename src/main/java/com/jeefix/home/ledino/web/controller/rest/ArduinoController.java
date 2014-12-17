package com.jeefix.home.ledino.web.controller.rest;

import com.jeefix.home.ledino.web.controller.rest.model.ResponseStatus;
import com.jeefix.home.ledino.web.controller.rest.model.ResponseWrapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeefix.home.ledino.common.helper.ColorHelper;
import com.jeefix.home.ledino.common.model.LedColor;
import com.jeefix.home.ledino.common.model.arduino.ArduinoState;
import com.jeefix.home.ledino.common.model.arduino.LedChannel;
import com.jeefix.home.ledino.logic.arduino.ArduinoService;

import javax.xml.ws.Response;

@Controller
@RequestMapping("/rest/arduino")
public class ArduinoController {
    
    private static final Logger log = Logger.getLogger(ArduinoController.class);
    
    @Autowired
    private ArduinoService arduinoService;
    
    @RequestMapping("/state")
    @ResponseBody
    public ResponseWrapper getStatus() {
        log.info(String.format("Received request to send arduino state"));
        ArduinoState arduinoState = arduinoService.getArduinoState();
        if (log.isDebugEnabled()) {
            log.debug(String.format("Returned arduino state: %s", arduinoState));
        }
        return new ResponseWrapper(ResponseStatus._200,arduinoState);
    }
    
    @RequestMapping("/level/{color}/{value}")
    @ResponseBody
    public ResponseWrapper setLevel(@PathVariable("color") LedChannel channel, @PathVariable("value") int value) {
        log.info(String.format("Received request to change color of channel '%s' to '%s'", channel, value));
        LedColor ledColor = arduinoService.getArduinoState().getLedColor();
        ColorHelper.setChannelValue(ledColor, channel, value);
        arduinoService.setLedLevel(ledColor);
        return new ResponseWrapper(ResponseStatus._200,arduinoService.getArduinoState());
    }
}
