package com.jeefix.home.ledino.common.constant;

/**
 * appolication property keys
 *
 * @author iskramac
 * @since 23 wrz 2014
 */
public enum ApplicationProperty {


    ARDUINO_BAUDRATE("arduino.baudrate"),
    ARDUINO_PORT_NAME("arduino.port_name"),
    ARDUINO_PIN_RED("arduino.pin.red"),
    ARDUINO_PIN_GREEN("arduino.pin.green"),
    ARDUINO_PIN_BLUE("arduino.pin.blue"),
    FIRMATA_MOCK_ENABLED("app.firmata.mock.enabled"),
    APP_WEBSOCKET_CHANNEL_STATE_CHANGE_URL("app.websocket.channel.state_change.url"),
    APP_DEFAULT_LED_COLOR("app.led.color.default");

    private String propertyName;

    private ApplicationProperty(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyName(){
        return propertyName;
    }

}
