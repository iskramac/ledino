package com.jeefix.home.ledino.model;

import java.util.HashMap;

import com.jeefix.home.ledino.common.enums.ArduinoMode;
import com.jeefix.home.ledino.common.enums.LedColor;

public class ArduinoState {

  private ArduinoMode currentMode;

  private HashMap<LedColor, Integer> ledLevelMap;

  public ArduinoMode getCurrentMode() {
    return currentMode;
  }

  public void setCurrentMode(ArduinoMode currentMode) {
    this.currentMode = currentMode;
  }

  public HashMap<LedColor, Integer> getLedLevelMap() {
    return ledLevelMap;
  }

  public void setLedLevelMap(HashMap<LedColor, Integer> ledLevelMap) {
    this.ledLevelMap = ledLevelMap;
  }



}
