package com.jeefix.home.ledino.logic;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import com.jeefix.home.ledino.common.enums.LedChannel;
import com.jeefix.home.ledino.common.exception.LedinoRuntimeException;

public abstract class FirmataAdapter {

  private static final Logger log = Logger.getLogger(FirmataAdapter.class);
  @Value("${arduino.pin.red}")
  private int redPinValue;
  @Value("${arduino.pin.green}")
  private int greenPinValue;
  @Value("${arduino.pin.blue}")
  private int bluePinValue;

  @Value("${arduino.port_name}")
  private String arduinoPortName;

  @Value("${arduino.baudrate}")
  private int arduinoBaudrate;

  @Value("${firmata.use_mock}")
  private boolean useMock;

  protected abstract void setPwmLevel(int pinNumber, int level) throws Exception;

  public void init() {};

  public void destroy() {};

  public void setLedLevel(LedChannel color, int level) {
    if (isUseMock()) {
      log.info("Ledino is in MOCK mode, changes has not been applied");
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
