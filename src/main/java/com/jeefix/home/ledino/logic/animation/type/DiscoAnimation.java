package com.jeefix.home.ledino.logic.animation.type;

import com.jeefix.home.ledino.common.exception.LedinoRuntimeException;
import com.jeefix.home.ledino.common.model.LedColor;
import com.jeefix.home.ledino.logic.arduino.ArduinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DiscoAnimation extends AnimationBase {
    
    public static final String ANIMATION_NAME = "Disco";
    
    private int internalIndex = 0;
    
    @Autowired
    private ArduinoService arduinoService;
    
    @Override
    public String getName() {
        return ANIMATION_NAME;
    }
    
    @Override
    public LedColor updateColor(LedColor current) {
        internalIndex = (++internalIndex) % 3 + 1;
        LedColor color = new LedColor();
        color.setBlue(internalIndex == 1 ? 255 : 0);
        color.setRed(internalIndex == 2 ? 255 : 0);
        color.setGreen(internalIndex == 3 ? 255 : 0);
        
        try {
            Thread.sleep(250);// TODO do propsów
        } catch (InterruptedException e) {
            throw new LedinoRuntimeException("Thread sleep has been interupted");
        }
        return color;
    }
    
}
