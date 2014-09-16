package com.jeefix.home.ledino.model;

import java.io.Serializable;

public class ApplicationConfiguration implements Serializable {

  private static final long serialVersionUID = -280678102574600209L;

  private String applicationHost;
  private ArduinoState arduinoState;

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
