package com.jeefix.home.logic;

import org.junit.Test;

import com.shigeodayo.javarduino.Arduino;

public class JavarduinoTest {

  @Test
  public void foo(){
    Arduino arduino = new Arduino(Arduino.list()[0], 57600);
    arduino.analogWrite(3, 255);
  }
}
