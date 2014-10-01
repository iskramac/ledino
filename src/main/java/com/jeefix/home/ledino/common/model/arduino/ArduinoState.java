package com.jeefix.home.ledino.common.model.arduino;

import java.io.Serializable;

import com.jeefix.home.ledino.common.model.LedColor;

public class ArduinoState implements Serializable {
    
    private static final long serialVersionUID = -438758483383986263L;
    
    private ArduinoMode arduinoMode;
    private LedColor ledColor;
    
    public ArduinoMode getArduinoMode() {
        return arduinoMode;
    }
    
    public void setArduinoMode(ArduinoMode arduinoMode) {
        this.arduinoMode = arduinoMode;
    }
    
    public LedColor getLedColor() {
        return ledColor;
    }
    
    public void setLedColor(LedColor ledColor) {
        this.ledColor = ledColor;
    }
    
}
