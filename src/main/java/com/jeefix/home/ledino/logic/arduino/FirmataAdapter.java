package com.jeefix.home.ledino.logic.arduino;

import static com.jeefix.home.ledino.common.constant.ApplicationProperties.*;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import com.jeefix.home.ledino.common.exception.LedinoRuntimeException;
import com.jeefix.home.ledino.common.model.arduino.LedChannel;

public abstract class FirmataAdapter {
    
    private static final Logger log = Logger.getLogger(FirmataAdapter.class);
    @Value(ARDUINO_PIN_RED)
    private int redPinValue;
    @Value(ARDUINO_PIN_GREEN)
    private int greenPinValue;
    @Value(ARDUINO_PIN_BLUE)
    private int bluePinValue;
    
    @Value(ARDUINO_PORT_NAME)
    private String arduinoPortName;
    
    @Value(ARDUINO_BAUDRATE)
    private int arduinoBaudrate;
    
    @Value(FIRMATA_MOCK_ENABLED)
    private boolean useMock;
    
    protected abstract void setPwmLevel(int pinNumber, int level) throws Exception;
    
    public void init() {};
    
    public void destroy() {};
    
    public void setLedLevel(LedChannel color, int level) {
        if (isUseMock()) {
            log.trace("Ledino is in MOCK mode, changes has not been applied");
            return;
        }
        int pinNumber = getLedPinNumber(color);
        try {
            setPwmLevel(pinNumber, level);
            log.info(String.format("PWM pin '%s' has been set to '%s'", pinNumber, level));
        } catch (Exception e) {
            throw new LedinoRuntimeException(String.format("Unable to set '%s' led level to '%s'", color, level), e);
        }
    }
    
    protected int getLedPinNumber(LedChannel color) {
        switch (color) {
            case BLUE:
                return bluePinValue;
            case GREEN:
                return greenPinValue;
            case RED:
                return redPinValue;
            default: {
                throw new LedinoRuntimeException(String.format("Unhadled color: %s", color));
            }
        }
        
    }
    
    public String getArduinoPortName() {
        return arduinoPortName;
    }
    
    public void setArduinoPortName(String arduinoPortName) {
        this.arduinoPortName = arduinoPortName;
    }
    
    public int getArduinoBaudrate() {
        return arduinoBaudrate;
    }
    
    public void setArduinoBaudrate(int arduinoBaudrate) {
        this.arduinoBaudrate = arduinoBaudrate;
    }
    
    public boolean isUseMock() {
        return useMock;
    }
    
    public void setUseMock(boolean useMock) {
        this.useMock = useMock;
    }
    
    public int getRedPinValue() {
        return redPinValue;
    }
    
    public void setRedPinValue(int redPinValue) {
        this.redPinValue = redPinValue;
    }
    
    public int getGreenPinValue() {
        return greenPinValue;
    }
    
    public void setGreenPinValue(int greenPinValue) {
        this.greenPinValue = greenPinValue;
    }
    
    public int getBluePinValue() {
        return bluePinValue;
    }
    
    public void setBluePinValue(int bluePinValue) {
        this.bluePinValue = bluePinValue;
    }
    
}
