package com.jeefix.home.ledino.logic.animation.type;

import java.util.Map;

import com.jeefix.home.ledino.common.model.LedColor;

public abstract class AnimationBase{

    private Map<String, String> properties;

    public Map<String, String> getProperties() {
        return properties;
    }
    
    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }
    
    public abstract String getName();

    public abstract LedColor updateColor(LedColor current);


}
