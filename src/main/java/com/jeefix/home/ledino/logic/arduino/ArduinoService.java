package com.jeefix.home.ledino.logic.arduino;

import com.jeefix.home.ledino.common.constant.ApplicationProperty;
import com.jeefix.home.ledino.common.helper.ColorHelper;
import com.jeefix.home.ledino.common.helper.PropertiesHelper;
import com.jeefix.home.ledino.common.model.LedColor;
import com.jeefix.home.ledino.common.model.arduino.ArduinoMode;
import com.jeefix.home.ledino.common.model.arduino.ArduinoState;
import com.jeefix.home.ledino.common.model.arduino.LedChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.locks.ReentrantLock;

@Service
@Scope("singleton")
public class ArduinoService {

    private static final Logger log = LoggerFactory.getLogger(ArduinoService.class);
    private String ledinoStateChangeTopic;
    private ReentrantLock lock  = new ReentrantLock();
    private ArduinoState arduinoState;

    @Autowired
    private FirmataAdapter firmataAdapter;
    @Autowired
    private PropertiesHelper propertiesHelper;
    @Autowired
    private SimpMessagingTemplate template;

    @PostConstruct
    public void init() {
        ledinoStateChangeTopic = propertiesHelper.getProperty(ApplicationProperty.APP_WEBSOCKET_CHANNEL_STATE_CHANGE_URL);
        int defaultColor = propertiesHelper.getIntProperty(ApplicationProperty.APP_DEFAULT_LED_COLOR);

        arduinoState = new ArduinoState(ArduinoMode.MANUAL,new LedColor(defaultColor, defaultColor, defaultColor));
        setLedLevel(arduinoState.getLedColor());
        log.info(String.format("Initialized service with default arduino state: %s", arduinoState));
    }

    public void setLedLevel(LedColor color) {
        lock.lock();
        try {
            for (LedChannel channel : LedChannel.values()) {
                int channelLevel = ColorHelper.getChannelValue(color, channel);
                firmataAdapter.setLedLevel(channel, channelLevel);
                arduinoState = new ArduinoState(arduinoState.getArduinoMode(),color);
                template.convertAndSend(ledinoStateChangeTopic, getArduinoState());
                log.debug("Changed channel '{}' level to '{}'", channel, channelLevel);
            }
        } finally {
            lock.unlock();
        }
    }

    public ArduinoState getArduinoState() {
        return arduinoState;
    }
}
