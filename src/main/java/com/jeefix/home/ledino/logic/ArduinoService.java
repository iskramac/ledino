package com.jeefix.home.ledino.logic;

import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jeefix.home.ledino.common.enums.ArduinoMode;
import com.jeefix.home.ledino.common.enums.LedChannel;
import com.jeefix.home.ledino.common.helper.ColorHelper;
import com.jeefix.home.ledino.common.helper.ObjectCloner;
import com.jeefix.home.ledino.model.ArduinoState;
import com.jeefix.home.ledino.model.LedColor;

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
    arduinoState.setArduinoMode(ArduinoMode.MANUAL);

    lock = new ReentrantLock();
    arduinoState.setLedColor(new LedColor(INITIAL_LED_LEVEL_VALUE, INITIAL_LED_LEVEL_VALUE, INITIAL_LED_LEVEL_VALUE));
    setLedLevel(arduinoState.getLedColor());
    log.info(String.format("Initialized service with default arduino state: %s", arduinoState));
  }

  public void setLedLevel(LedColor color) {
    lock.lock();
    try {
      for (LedChannel channel : LedChannel.values()) {
        int channelLevel = ColorHelper.getChannelValue(color, channel);
        firmataAdapter.setLedLevel(channel, channelLevel);
        arduinoState.setLedColor(color);
        log.debug(String.format("Changed channel '%s' level to '%d'", channel, channelLevel));
      }
    } finally {
      lock.unlock();
    }
  }

  public ArduinoState getArduinoState() {
    return ObjectCloner.deepCopy(arduinoState);
  }
}
