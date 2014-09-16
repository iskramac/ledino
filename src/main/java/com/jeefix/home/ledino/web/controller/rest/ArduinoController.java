package com.jeefix.home.ledino.web.controller.rest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeefix.home.ledino.common.enums.LedChannel;
import com.jeefix.home.ledino.common.helper.ColorHelper;
import com.jeefix.home.ledino.logic.ArduinoService;
import com.jeefix.home.ledino.model.ArduinoState;
import com.jeefix.home.ledino.model.LedColor;

@Controller
@RequestMapping("/rest")
public class ArduinoController {
  private static final Logger log = Logger.getLogger(ArduinoController.class);

  @Autowired
  private ArduinoService arduinoService;

  @RequestMapping("/arduino/state")
  @ResponseBody
  public ArduinoState getStatus() {
    log.info(String.format("Received request to send arduino state"));
    ArduinoState arduinoState = arduinoService.getArduinoState();
    if (log.isDebugEnabled()) {
      log.debug(String.format("Returned arduino state: %s", arduinoState));
    }
    return arduinoState;
  }

  @RequestMapping("/arduino/level/{color}/{value}")
  @ResponseBody
  public ArduinoState setLevel(@PathVariable("color") LedChannel channel, @PathVariable("value") int value) {
    log.info(String.format("Received request to change color of channel '%s' to '%s'", channel, value));
    LedColor ledColor = arduinoService.getArduinoState().getLedColor();
    ColorHelper.setChannelValue(ledColor, channel, value);
    arduinoService.setLedLevel(ledColor);
    return arduinoService.getArduinoState();
  }
}