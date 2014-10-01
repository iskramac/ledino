package com.jeefix.home.ledino.logic.arduino;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.jeefix.home.ledino.common.exception.FirmataInitalizationException;
import com.shigeodayo.javarduino.Arduino;

@Lazy
@Service
public class FirmataAdapterJavarduino extends FirmataAdapter {
    
    private static final Logger log = Logger.getLogger(FirmataAdapterJavarduino.class);
    
    private Arduino javarduino;
    
    @PostConstruct
    public void init() {
        if (isUseMock()) {
            log.info("Omitting initialization as 'useMock' flag is set to 'true'");
            return;
        }
        try {
            log.info(String.format("Attempting to connect to arduino at port '%s' with baudrate '%s'", getArduinoPortName(),
                getArduinoBaudrate()));
            
            javarduino = new Arduino(getArduinoPortName(), getArduinoBaudrate());
            javarduino.pinMode(getGreenPinValue(), Arduino.PWM);
            javarduino.pinMode(getRedPinValue(), Arduino.PWM);
            javarduino.pinMode(getBluePinValue(), Arduino.PWM);
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
        if (javarduino != null) {
            javarduino.dispose();
        }
        
    }
    
}
