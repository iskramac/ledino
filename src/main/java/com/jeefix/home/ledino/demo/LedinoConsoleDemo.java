package com.jeefix.home.ledino.demo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;

import com.jeefix.home.ledino.common.enums.LedColor;
import com.jeefix.home.ledino.logic.FirmataAdapter;
import com.jeefix.home.ledino.logic.FirmataAdapterJavarduino;
import com.shigeodayo.javarduino.Arduino;

public class LedinoConsoleDemo {

  public static void main(String[] args) throws Exception {
    FirmataAdapter adapter = new FirmataAdapterJavarduino();
    Properties props = new Properties();
    props.load(LedinoConsoleDemo.class.getResourceAsStream("/application.properties"));

    adapter.setArduinoBaudrate(Integer.parseInt(props.getProperty("arduino.baudrate")));
    adapter.setArduinoPortName(props.getProperty("arduino.port_name"));
    adapter.init();
    System.out.println("START");
    adapter.setLedLevel(LedColor.RED, 255);
    Thread.sleep(300);
    adapter.setLedLevel(LedColor.GREEN, 255);
    Thread.sleep(300);
    adapter.setLedLevel(LedColor.BLUE, 255);
    Thread.sleep(300);
    adapter.setLedLevel(LedColor.BLUE, 0);
    Thread.sleep(300);
    adapter.setLedLevel(LedColor.GREEN, 0);
    Thread.sleep(300);
    adapter.setLedLevel(LedColor.RED, 0);
  }
}
