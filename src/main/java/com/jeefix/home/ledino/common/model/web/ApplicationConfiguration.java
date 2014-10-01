package com.jeefix.home.ledino.common.model.web;

import java.io.Serializable;

import com.jeefix.home.ledino.common.model.arduino.ArduinoState;

public class ApplicationConfiguration implements Serializable {
    
    private static final long serialVersionUID = -280678102574600209L;
    
    private String applicationHost;
    private ArduinoState arduinoState;
    private String arduinoStateBroadcastUrl;
    
    public String getArduinoStateBroadcastUrl() {
        return arduinoStateBroadcastUrl;
    }
    
    public void setArduinoStateBroadcastUrl(String arduinoStateBroadcastUrl) {
        this.arduinoStateBroadcastUrl = arduinoStateBroadcastUrl;
    }
    
    public String getApplicationHost() {
        return applicationHost;
    }
    
    public void setApplicationHost(String applicationHost) {
        this.applicationHost = applicationHost;
    }
    
    public ArduinoState getArduinoState() {
        return arduinoState;
    }
    
    public void setArduinoState(ArduinoState arduinoState) {
        this.arduinoState = arduinoState;
    }
    
}
