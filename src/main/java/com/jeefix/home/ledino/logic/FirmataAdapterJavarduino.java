package com.jeefix.home.ledino.logic;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import name.antonsmirnov.firmata.message.SetPinModeMessage;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.jeefix.home.ledino.common.exception.FirmataInitalizationException;
import com.shigeodayo.javarduino.Arduino;

@Lazy
@Service("FirmataAdapterJavarduino")
public class FirmataAdapterJavarduino extends FirmataAdapter {

  private static final Logger log = Logger.getLogger(FirmataAdapterJavarduino.class);

  private Arduino javarduino;

  @PostConstruct
  public void init() {
    if(isUseMock()){
      log.info("Omitting initialization as 'useMock' flag is set to 'true'");
      return;
    }
    try {
      log.info(String.format("Attempting to connect to arduino at port '%s' with baudrate '%s'",
          getArduinoPortName(), getArduinoBaudrate()));
      
      javarduino = new Arduino(getArduinoPortName(), getArduinoBaudrate());
      log.info("Successfuly connected to arduino");
    } catch (Exception e) {
      throw new FirmataInitalizationException("Unable to connect to arduino", e);
    }
  }

  @Override
  protected void setPwmLevel(int pinNumber, int level) throws Exception {
    javarduino.analogWrite(pinNumber, level);

  }

  @PreDestroy
  public void destroy() {
    javarduino.dispose();
  }



}
