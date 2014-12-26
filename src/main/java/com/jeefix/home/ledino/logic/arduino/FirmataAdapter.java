package com.jeefix.home.ledino.logic.arduino;

import com.jeefix.home.ledino.common.constant.ApplicationProperty;
import com.jeefix.home.ledino.common.exception.LedinoRuntimeException;
import com.jeefix.home.ledino.common.helper.PropertiesHelper;
import com.jeefix.home.ledino.common.model.arduino.LedChannel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public abstract class FirmataAdapter {

    private static final Logger log = Logger.getLogger(FirmataAdapter.class);

    private int redPinValue;
    private int greenPinValue;
    private int bluePinValue;
    private String arduinoPortName;
    private int arduinoBaudrate;
    private boolean useMock;

    @Autowired
    private PropertiesHelper propertiesHelper;

    protected abstract void setPwmLevel(int pinNumber, int level) throws Exception;

    @PostConstruct
    public void initProperties() {
        redPinValue = propertiesHelper.getIntProperty(ApplicationProperty.ARDUINO_PIN_RED);
        greenPinValue = propertiesHelper.getIntProperty(ApplicationProperty.ARDUINO_PIN_GREEN);
        bluePinValue = propertiesHelper.getIntProperty(ApplicationProperty.ARDUINO_PIN_BLUE);

        arduinoPortName = propertiesHelper.getProperty(ApplicationProperty.ARDUINO_PORT_NAME);
        arduinoBaudrate = propertiesHelper.getIntProperty(ApplicationProperty.ARDUINO_BAUDRATE);
        useMock = propertiesHelper.getBooleanProperty(ApplicationProperty.FIRMATA_MOCK_ENABLED);
    }

    ;

    public void destroy() {
    }

    ;

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
