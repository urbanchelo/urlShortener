package com.infobip.urlshortener.exception;

public class InvalidParamException extends RuntimeException {

  public InvalidParamException(final String message) {
    super(message);
  }
}
