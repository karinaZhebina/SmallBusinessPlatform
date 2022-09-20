package com.sbp.controller.exception;

public class ControllerException extends Exception {
  public ControllerException(Throwable cause) {
    super(cause);
  }

  public ControllerException(String message) {
    super(message);
  }
}
