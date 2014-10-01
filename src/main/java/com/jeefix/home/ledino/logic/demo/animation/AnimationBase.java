package com.jeefix.home.ledino.logic.demo.animation;

import com.jeefix.home.ledino.common.model.demo.AnimationStatus;

public abstract class AnimationBase implements Runnable {
    
    private AnimationStatus status;
    
    public AnimationBase() {
        status = AnimationStatus.STOPED;
    }
    
    public AnimationStatus getStatus() {
        return status;
    }
    
    public void setStatus(AnimationStatus status) {
        this.status = status;
    }
    
    public abstract String getName();
    
}
