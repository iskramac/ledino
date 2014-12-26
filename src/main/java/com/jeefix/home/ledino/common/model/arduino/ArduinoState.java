package com.jeefix.home.ledino.common.model.arduino;

import java.io.Serializable;

import com.jeefix.home.ledino.common.model.LedColor;

public class ArduinoState implements Serializable {
    
    private static final long serialVersionUID = -438758483383986263L;
    
    private ArduinoMode arduinoMode;
    private LedColor ledColor;

    public ArduinoState(ArduinoMode arduinoMode, LedColor ledColor) {
        this.arduinoMode = arduinoMode;
        this.ledColor = ledColor;
    }

    public ArduinoMode getArduinoMode() {
        return arduinoMode;
    }
    public LedColor getLedColor() {
        return ledColor;
    }
}
