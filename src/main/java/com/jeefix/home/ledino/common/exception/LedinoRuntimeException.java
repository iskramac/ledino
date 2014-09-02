package com.jeefix.home.ledino.common.exception;

public class LedinoRuntimeException extends RuntimeException {

  private static final long serialVersionUID = 8148128102748434825L;

  public LedinoRuntimeException() {
    super();
  }


  public LedinoRuntimeException(String message, Throwable cause) {
    super(message, cause);
  }

  public LedinoRuntimeException(String message) {
    super(message);
  }

  public LedinoRuntimeException(Throwable cause) {
    super(cause);
  }


}
