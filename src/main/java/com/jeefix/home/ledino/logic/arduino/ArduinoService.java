package com.jeefix.home.ledino.logic.arduino;

import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.jeefix.home.ledino.common.constant.ApplicationPropertyExpression;
import com.jeefix.home.ledino.common.helper.ColorHelper;
import com.jeefix.home.ledino.common.helper.ObjectCloner;
import com.jeefix.home.ledino.common.model.LedColor;
import com.jeefix.home.ledino.common.model.arduino.ArduinoMode;
import com.jeefix.home.ledino.common.model.arduino.ArduinoState;
import com.jeefix.home.ledino.common.model.arduino.LedChannel;

@Service
@Scope("singleton")
public class ArduinoService {
    
    @Value(ApplicationPropertyExpression.APP_WEBSOCKET_CHANNEL_STATE_CHANGE_URL)
    private String ledinoStateChangeTopic;
    
    @Autowired
    private SimpMessagingTemplate template;
    
    private static final int INITIAL_LED_LEVEL_VALUE = 0;
    private static final Logger log = Logger.getLogger(ArduinoService.class);
    private ReentrantLock lock;
    private ArduinoState arduinoState;
    
    @Autowired
    private FirmataAdapter firmataAdapter;
    
    @PostConstruct
    public void init() {
        arduinoState = new ArduinoState();
        arduinoState.setArduinoMode(ArduinoMode.MANUAL);
        
        lock = new ReentrantLock();
        arduinoState.setLedColor(new LedColor(INITIAL_LED_LEVEL_VALUE, INITIAL_LED_LEVEL_VALUE, INITIAL_LED_LEVEL_VALUE));
        setLedLevel(arduinoState.getLedColor());
        log.info(String.format("Initialized service with default arduino state: %s", arduinoState));
    }
    
    public void setLedLevel(LedColor color) {
        lock.lock();
        try {
            for (LedChannel channel : LedChannel.values()) {
                int channelLevel = ColorHelper.getChannelValue(color, channel);
                firmataAdapter.setLedLevel(channel, channelLevel);
                arduinoState.setLedColor(color);
                template.convertAndSend(ledinoStateChangeTopic, getArduinoState());
                log.debug(String.format("Changed channel '%s' level to '%d'", channel, channelLevel));
            }
        } finally {
            lock.unlock();
        }
    }
    
    public ArduinoState getArduinoState() {
        return ObjectCloner.deepCopy(arduinoState);
    }
}
