package com.jeefix.home.ledino.logic;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import name.antonsmirnov.firmata.Firmata;
import name.antonsmirnov.firmata.message.AnalogMessage;
import name.antonsmirnov.firmata.message.SetPinModeMessage;
import name.antonsmirnov.firmata.serial.ISerial;
import name.antonsmirnov.firmata.serial.IndepProcessingSerialAdapter;
import name.antonsmirnov.firmata.serial.SerialException;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import processing.serial.IndepProcessingSerial;

import com.jeefix.home.ledino.common.exception.FirmataInitalizationException;
import com.jeefix.home.ledino.common.exception.LedinoRuntimeException;

@Lazy
@Service("FirmataAdapterAntonsmirnow")
public class FirmataAdapterAntonsmirnow extends FirmataAdapter {

  private static final Logger log = Logger.getLogger(FirmataAdapterAntonsmirnow.class);
  private Firmata firmata;

  @PostConstruct
  public void init() {
    if (isUseMock()) {
      return;
    }
    try {

      ISerial serial =
          new IndepProcessingSerialAdapter(new IndepProcessingSerial(getArduinoPortName(),
              getArduinoBaudrate()));
      firmata = new Firmata(serial);
      serial.start();
      log.info("Successfuly connected to arduino");
      firmata.send(new SetPinModeMessage(LED_PIN_RED, SetPinModeMessage.PIN_MODE.PWM.getMode()));
      firmata.send(new SetPinModeMessage(LED_PIN_GREEN, SetPinModeMessage.PIN_MODE.PWM.getMode()));
      firmata.send(new SetPinModeMessage(LED_PIN_BLUE, SetPinModeMessage.PIN_MODE.PWM.getMode()));
      log.info(String.format("Successfuly initialized RGB pins on PWM mode"));
    } catch (Exception e) {
      throw new FirmataInitalizationException("Unable to connect to arduino", e);
    }
  }


  @PreDestroy
  public void destroy() {
    try {
      firmata.getSerial().stop();
    } catch (SerialException e) {
      throw new LedinoRuntimeException("Unable to stop serial port", e);
    }
  }


  @Override
  protected void setPwmLevel(int pinNumber, int level) throws Exception {
    firmata.send(new AnalogMessage(pinNumber, level));
  }



  public Firmata getFirmata() {
    return firmata;
  }


  public void setFirmata(Firmata firmata) {
    this.firmata = firmata;
  }


}
