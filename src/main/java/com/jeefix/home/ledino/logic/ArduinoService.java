package com.jeefix.home.ledino.logic;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jeefix.home.ledino.common.enums.ArduinoMode;
import com.jeefix.home.ledino.common.enums.LedColor;
import com.jeefix.home.ledino.model.ArduinoState;

@Service
@Scope("singleton")
public class ArduinoService {

  private static final int INITIAL_LED_LEVEL_VALUE = 0;
  private static final Logger log = Logger.getLogger(ArduinoService.class);
  private ReentrantLock lock;
  private ArduinoState arduinoState;

  @Autowired
  @Qualifier("FirmataAdapterJavarduino")
  private FirmataAdapter firmataAdapter;

  @PostConstruct
  public void init() {
    arduinoState = new ArduinoState();
    arduinoState.setCurrentMode(ArduinoMode.MANUAL);
    arduinoState.setLedLevelMap(new HashMap<LedColor, Integer>());
    lock = new ReentrantLock();
    for (LedColor color : LedColor.values()) {
      arduinoState.getLedLevelMap().put(color, INITIAL_LED_LEVEL_VALUE);
      firmataAdapter.setLedLevel(color, INITIAL_LED_LEVEL_VALUE);
    }
    log.info(String.format("Initialized service with default arduino state: %s", arduinoState));
  }

  public void setLedLevel(LedColor color, int level) {
    lock.lock();
    try {
      arduinoState.getLedLevelMap().put(color, level);
      firmataAdapter.setLedLevel(color, level);
    } finally {
      lock.unlock();
    }
  }

  public ArduinoState getArduinoState() {
    return arduinoState;
  }
}
