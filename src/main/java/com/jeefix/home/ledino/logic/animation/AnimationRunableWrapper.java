package com.jeefix.home.ledino.logic.animation;

import com.jeefix.home.ledino.common.model.LedColor;
import com.jeefix.home.ledino.logic.animation.type.AnimationBase;
import com.jeefix.home.ledino.logic.arduino.ArduinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Wraps type into runnable task
 */
@Component
@Scope("singleton")
class AnimationRunableWrapper implements Runnable {


    protected AnimationBase animation;
    protected AnimationStatus status = AnimationStatus.UNKNOWN;
    @Autowired
    private ArduinoService arduinoService;

    @Override
    public void run() {
        status = AnimationStatus.IN_PROGESS;

        while (AnimationStatus.IN_PROGESS.equals(status)) {
            LedColor currentColor = arduinoService.getArduinoState().getLedColor();
            LedColor newColor = getAnimation().updateColor(currentColor);

            arduinoService.setLedLevel(newColor);

            if (newColor.equals(currentColor)) { //same colors, nothing to be done more
                status = AnimationStatus.SHOUD_BE_TERMINATED;
            }
        }
        status = AnimationStatus.STOPED;
    }

    public AnimationStatus getStatus() {
        return status;
    }

    public void setStatus(AnimationStatus status) {
        this.status = status;
    }

    public AnimationBase getAnimation() {
        return animation;
    }

    public void setAnimation(AnimationBase animation) {
        this.animation = animation;
    }


}
