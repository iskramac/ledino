package com.jeefix.home.ledino.common.constant;

/**
 * appolication property keys
 *
 * @author iskramac
 * @since 23 wrz 2014
 *
 */
public class ApplicationPropertyExpression {
    
    public static final String ARDUINO_BAUDRATE = "${arduino.baudrate}";
    public static final String ARDUINO_PORT_NAME = "${arduino.port_name}";
    public static final String ARDUINO_PIN_RED = "${arduino.pin.red}";
    public static final String ARDUINO_PIN_GREEN = "${arduino.pin.green}";
    public static final String ARDUINO_PIN_BLUE = "${arduino.pin.blue}";
    
    public static final String FIRMATA_MOCK_ENABLED = "${app.firmata.mock.enabled}";
    
    public static final String APP_WEBSOCKET_CHANNEL_STATE_CHANGE_URL = "${app.websocket.channel.state_change.url}";
    
}
