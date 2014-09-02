package com.jeefix.home.ledino.logic;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Value;

import name.antonsmirnov.firmata.Firmata;
import name.antonsmirnov.firmata.message.AnalogMessage;

import com.jeefix.home.ledino.common.enums.LedColor;
import com.jeefix.home.ledino.common.exception.LedinoRuntimeException;



public abstract class FirmataAdapter {

  protected static final int LED_PIN_RED = 5;
  protected static final int LED_PIN_GREEN = 6;
  protected static final int LED_PIN_BLUE = 3;

  @Value("${arduino.port_name}")
  private String arduinoPortName;

  @Value("${arduino.baudrate}")
  private int arduinoBaudrate;

  @Value("${firmata.use_mock}")
  private boolean useMock;

  protected abstract void setPwmLevel(int pinNumber, int level) throws Exception;

  public void init() {};
  public void destroy() {};
  
  public void setLedLevel(LedColor color, int level) {
    if (isUseMock()) {
      return;
    }
    int pinNumber = getLedPinNumber(color);
    try {
      setPwmLevel(pinNumber, level);
    } catch (Exception e) {
      throw new LedinoRuntimeException(String.format("Unable to set '%s' led level to '%s'", color,
          level), e);
    }
  }

  protected int getLedPinNumber(LedColor color) {
    // TODO refactor when props file introduced
    switch (color) {
      case BLUE:
        return LED_PIN_BLUE;
      case GREEN:
        return LED_PIN_GREEN;
      case RED:
        return LED_PIN_RED;
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



}
