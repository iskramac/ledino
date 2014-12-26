package com.jeefix.home.logic;

import com.jeefix.home.ledino.common.model.arduino.LedChannel;
import com.jeefix.home.ledino.logic.arduino.FirmataAdapter;
import com.jeefix.home.ledino.logic.arduino.FirmataAdapterJavarduino;

public class FirmataAdapterTest {
    
    private static final String PORT_NAME = "COM5";// "/dev/serial/by-id/usb-Arduino__www.arduino.cc__0043_74132343430351205262-if00";
    
    @org.junit.Test
    public void shouldAllChannelsBlink() throws InterruptedException {
        FirmataAdapter adapter = new FirmataAdapterJavarduino();
        adapter.setArduinoBaudrate(57600);
        adapter.setArduinoPortName(PORT_NAME);
        adapter.setUseMock(false);
        System.out.println("AAAA");
//        adapter.init();
        System.out.println("STOP");
        adapter.setLedLevel(LedChannel.RED, 255);
        Thread.sleep(300);
        adapter.setLedLevel(LedChannel.GREEN, 255);
        Thread.sleep(300);
        adapter.setLedLevel(LedChannel.BLUE, 255);
        Thread.sleep(300);
        adapter.setLedLevel(LedChannel.BLUE, 0);
        Thread.sleep(300);
        adapter.setLedLevel(LedChannel.GREEN, 0);
        Thread.sleep(300);
        adapter.setLedLevel(LedChannel.RED, 0);
        
    }
}
