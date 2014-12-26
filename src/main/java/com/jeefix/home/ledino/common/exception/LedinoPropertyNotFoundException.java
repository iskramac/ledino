package com.jeefix.home.ledino.common.exception;

import com.jeefix.home.ledino.common.constant.ApplicationProperty;

/**
 * Created by mais on 2014-12-26.
 */
public class LedinoPropertyNotFoundException extends LedinoRuntimeException {

    public LedinoPropertyNotFoundException() {
        super();
    }

    public LedinoPropertyNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public LedinoPropertyNotFoundException(String message) {
        super(message);
    }

    public LedinoPropertyNotFoundException(Throwable cause) {
        super(cause);
    }
    public LedinoPropertyNotFoundException(ApplicationProperty property){
        super(String.format("Unable to find property '%s'",property.getPropertyName()));
    }

}
