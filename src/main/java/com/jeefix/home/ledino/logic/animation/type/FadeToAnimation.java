package com.jeefix.home.ledino.logic.animation.type;

import com.jeefix.home.ledino.common.exception.LedinoRuntimeException;
import com.jeefix.home.ledino.common.model.LedColor;
import org.springframework.stereotype.Component;

@Component
public class FadeToAnimation extends AnimationBase {
    
    public static final String ANIMATION_NAME = "FadeTo";
    
    public static final String PROP_RED_VAL = "RED_VAL";
    public static final String PROP_GREEN_VAL = "GREEN_VAL";
    public static final String PROP_BLUE_VAL = "BLUE_VAL";
    
    @Override
    public String getName() {
        return ANIMATION_NAME;
    }
    
    @Override
    public LedColor updateColor(LedColor current) {
        LedColor color = new LedColor();
        color.setRed(getNewColorValue(current.getRed(), Integer.valueOf(getProperties().get(PROP_RED_VAL))));
        color.setGreen(getNewColorValue(current.getGreen(), Integer.valueOf(getProperties().get(PROP_GREEN_VAL))));
        color.setBlue(getNewColorValue(current.getBlue(), Integer.valueOf(getProperties().get(PROP_BLUE_VAL))));
        
        try {
            Thread.sleep(250);// TODO do propsów
        } catch (InterruptedException e) {
            throw new LedinoRuntimeException("Thread sleep has been interupted");
        }
        return color;
    }
    
    protected int getNewColorValue(int current, int finalColor) {
        if (current == finalColor) {
            return current;
        }
        if (current < finalColor) {
            return ++current;
        } else {
            return --current;
        }
        
    }
}
