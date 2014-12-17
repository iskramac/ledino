package com.jeefix.home.ledino.common.model;

import java.io.Serializable;

public class LedColor implements Serializable {

  private static final long serialVersionUID = 8951765633585640074L;

  private int red;
  private int green;
  private int blue;

  public LedColor(int red, int green, int blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  public LedColor() {}

  public int getRed() {
    return red;
  }

  public void setRed(int red) {
    this.red = red;
  }

  public int getGreen() {
    return green;
  }

  public void setGreen(int green) {
    this.green = green;
  }

  public int getBlue() {
    return blue;
  }

  public void setBlue(int blue) {
    this.blue = blue;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    LedColor ledColor = (LedColor) o;

    if (blue != ledColor.blue) return false;
    if (green != ledColor.green) return false;
    if (red != ledColor.red) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = red;
    result = 31 * result + green;
    result = 31 * result + blue;
    return result;
  }

  @Override
  public String toString() {
    return "LedColor [red=" + red + ", green=" + green + ", blue=" + blue + "]";
  }
}