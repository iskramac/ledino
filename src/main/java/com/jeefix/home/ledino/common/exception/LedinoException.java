package com.jeefix.home.ledino.common.exception;

public class LedinoException extends Exception {

  private static final long serialVersionUID = 287685913949251250L;

  public LedinoException() {
    super();
  }



  public LedinoException(String message, Throwable cause) {
    super(message, cause);
  }

  public LedinoException(String message) {
    super(message);
  }

  public LedinoException(Throwable cause) {
    super(cause);
  }

}
