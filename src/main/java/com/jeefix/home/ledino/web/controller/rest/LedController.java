package com.jeefix.home.ledino.web.controller.rest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeefix.home.ledino.common.enums.LedColor;
import com.jeefix.home.ledino.logic.ArduinoService;
import com.jeefix.home.ledino.model.ArduinoState;

@Controller
public class LedController {
  private static final Logger log = Logger.getLogger(LedController.class);

  @Autowired
  private ArduinoService arduinoService;

  @RequestMapping("/arduino/state")
  @ResponseBody
  public ArduinoState getStatus() {
    ArduinoState arduinoState = arduinoService.getArduinoState();
    if (log.isDebugEnabled()) {
      log.debug(String.format("Returned arduino state: %s", arduinoState));
    }
    return arduinoState;
  }

  @RequestMapping("/arduino/level/{color}/{value}")
  @ResponseBody
  public ArduinoState setLevel(@PathVariable("color") LedColor color, @PathVariable("value") int level) {
    arduinoService.setLedLevel(color, level);
    log.info(String.format("Changed arduino color '%s' level to '%s'", color, level));

    return arduinoService.getArduinoState();
  }

  @RequestMapping(value = "/arduino/levels", method = RequestMethod.POST)
  @ResponseBody
  public ArduinoState setLevel(@RequestParam("RED") int red, @RequestParam("GREEN") int green,
      @RequestParam("BLUE") int blue) {

    arduinoService.setLedLevel(LedColor.RED, red);
    arduinoService.setLedLevel(LedColor.GREEN, green);
    arduinoService.setLedLevel(LedColor.BLUE, blue);
    log.info(String.format("Changed arduino colors to: RED='%s', GREEN='%s', BLUE='%s'", red, green, blue));

    return arduinoService.getArduinoState();
  }

}
