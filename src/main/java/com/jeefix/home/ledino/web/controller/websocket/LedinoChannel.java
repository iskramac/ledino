package com.jeefix.home.ledino.web.controller.websocket;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.jeefix.home.ledino.logic.ArduinoService;
import com.jeefix.home.ledino.model.LedColor;

@Controller
public class LedinoChannel {
  private static final Logger log = Logger.getLogger(LedinoChannel.class);

  @Autowired
  private SimpMessagingTemplate template;

  @Autowired
  private ArduinoService arduinoService;

  @MessageMapping("/set-led-state")
  public String setLedState(LedColor color) {
    log.info(String.format("Recevied color change request to: %s", color));
    arduinoService.setLedLevel(color);
    template.convertAndSend("/topic/ledino-state-changed", arduinoService.getArduinoState());
    return "OK";
  }

  @MessageMapping("/broadcast-ledino-state")
  public String getArduinoState() {
    log.info("Recevied arduino state request");
    template.convertAndSend("/topic/ledino-state-changed", arduinoService.getArduinoState());
    return "OK";
  }

  //  @Scheduled(fixedRate = 2000)
  protected void broadcastState() {
    template.convertAndSend("/topic/ledino-state-changed", new LedColor(1, 2, 3));
    log.info("Pinag");
  }
}
