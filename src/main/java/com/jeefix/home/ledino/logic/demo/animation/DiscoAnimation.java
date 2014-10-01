package com.jeefix.home.ledino.logic.demo.animation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jeefix.home.ledino.common.exception.LedinoRuntimeException;
import com.jeefix.home.ledino.common.model.LedColor;
import com.jeefix.home.ledino.common.model.demo.AnimationStatus;
import com.jeefix.home.ledino.logic.arduino.ArduinoService;

@Component
public class DiscoAnimation extends AnimationBase {
    
    private static final int CHANGE_INTERVAL = 250;
    
    public static final String ANIMATION_NAME = "Disco";
    
    @Autowired
    private ArduinoService arduinoService;
    
    @Override
    public void run() {
        LedColor color = new LedColor(0, 0, 0);
        long index = 0;
        while (getStatus() == AnimationStatus.STARTED) {
            index = (++index) % 3 + 1;
            color.setBlue(index == 1 ? 255 : 0);
            color.setRed(index == 2 ? 255 : 0);
            color.setGreen(index == 3 ? 255 : 0);
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
