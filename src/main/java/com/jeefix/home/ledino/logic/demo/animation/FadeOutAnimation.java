package com.jeefix.home.ledino.logic.demo.animation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jeefix.home.ledino.common.exception.LedinoRuntimeException;
import com.jeefix.home.ledino.common.model.LedColor;
import com.jeefix.home.ledino.common.model.demo.AnimationStatus;
import com.jeefix.home.ledino.logic.arduino.ArduinoService;

@Component
public class FadeOutAnimation extends AnimationBase {
    
    public static final String ANIMATION_NAME = "FadeOut";
    private static final int CHANGE_INTERVAL = 250;
    
    @Autowired
    private ArduinoService arduinoService;
    
    @Override
    public void run() {
        LedColor color = arduinoService.getArduinoState().getLedColor();
        while ((color.getRed() > 0 || color.getBlue() > 0 && color.getGreen() > 0) && getStatus() == AnimationStatus.STARTED) {
            color.setRed(color.getRed() > 0 ? color.getRed() - 1 : 0);
            color.setGreen(color.getGreen() > 0 ? color.getGreen() - 1 : 0);
            color.setBlue(color.getBlue() > 0 ? color.getBlue() - 1 : 0);
            arduinoService.setLedLevel(color);
            try {
                Thread.sleep(CHANGE_INTERVAL);
            } catch (InterruptedException e) {
                throw new LedinoRuntimeException(e);
            }
        }
        setStatus(AnimationStatus.STOPED);
    }
    
    @Override
    public String getName() {
        return ANIMATION_NAME;
    }
    
}
