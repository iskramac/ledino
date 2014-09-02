package com.jeefix.home.logic;

import com.jeefix.home.ledino.common.enums.LedColor;
import com.jeefix.home.ledino.logic.FirmataAdapter;
import com.jeefix.home.ledino.logic.FirmataAdapterAntonsmirnow;
import com.jeefix.home.ledino.logic.FirmataAdapterJavarduino;

public class FirmataAdapterTest {

  private static final String PORT_NAME = "COM5";// "/dev/serial/by-id/usb-Arduino__www.arduino.cc__0043_74132343430351205262-if00";

  @org.junit.Test
  public void shouldAllChannelsBlink() throws InterruptedException {
    FirmataAdapter adapter = new FirmataAdapterJavarduino();
    adapter.setArduinoBaudrate(57600);
    adapter.setArduinoPortName(PORT_NAME);
    adapter.setUseMock(false);
    System.out.println("AAAA");
    adapter.init();
    System.out.println("STOP");
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
