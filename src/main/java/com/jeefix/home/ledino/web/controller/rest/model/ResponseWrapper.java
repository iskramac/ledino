package com.jeefix.home.ledino.web.controller.rest.model;


/**
 * Created by mais on 2014-12-17.
 */
public class ResponseWrapper {
    private Object payload;
    private ResponseStatus status;

    public static ResponseWrapper OK = new ResponseWrapper(ResponseStatus._200,"Success");

    public ResponseWrapper(ResponseStatus status, Object payload) {
        this.payload = payload;
        this.status = status;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }
}
