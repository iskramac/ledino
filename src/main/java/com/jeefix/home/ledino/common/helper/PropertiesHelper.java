package com.jeefix.home.ledino.common.helper;

import com.jeefix.home.ledino.common.constant.ApplicationConstants;
import com.jeefix.home.ledino.common.constant.ApplicationProperty;
import com.jeefix.home.ledino.common.exception.LedinoPropertyNotFoundException;
import com.jeefix.home.ledino.common.exception.LedinoRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by mais on 2014-12-26.
 */
@Component
public class PropertiesHelper {

    public static Logger log = LoggerFactory.getLogger(PropertiesHelper.class);
    private Properties props;

    @PostConstruct
    public void init() {
        String filePath = System.getProperty(ApplicationConstants.PROPERTIES_FILE_SYSTEM_PATH_KEY);
        if (filePath == null) {
            log.debug("External properties file has NOT been found, loading from internal file '{}'", ApplicationConstants.PROPERTIES_FILE_DEFAULT_NAME);
            filePath = getClass().getClassLoader().getResource(ApplicationConstants.PROPERTIES_FILE_DEFAULT_NAME).getPath();
        }
        try {
            props = new Properties();
            props.load(new FileInputStream(filePath));
            log.info("Loaded properties from file '{}'", filePath);
        } catch (FileNotFoundException e) {
            throw new LedinoRuntimeException("Unable to find properties file", e);
        } catch (IOException e) {
            throw new LedinoRuntimeException("Unable to load propertires", e);
        }
    }

    public String getProperty(ApplicationProperty propertyKey){
        String property =  props.getProperty(propertyKey.getPropertyName());
        if(property == null){
            throw new LedinoPropertyNotFoundException(propertyKey);
        }
        return property;
    }

    public int getIntProperty(ApplicationProperty property){
        return Integer.parseInt(getProperty(property));
    }
    public boolean getBooleanProperty(ApplicationProperty property){
        return Boolean.parseBoolean(getProperty(property));
    }

}
