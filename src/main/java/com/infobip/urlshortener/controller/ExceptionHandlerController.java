package com.infobip.urlshortener.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.infobip.urlshortener.exception.DataNotFoundException;
import com.infobip.urlshortener.exception.InvalidParamException;
import com.infobip.urlshortener.exception.UnauthorizedException;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@ControllerAdvice
public class ExceptionHandlerController {

  /**
   * @param exc exception that was thrown
   * @return error response for general exceptions with 500 code
   */
  @ExceptionHandler({Exception.class})
  public ResponseEntity<ErrorDto> handleException(Exception exc) {
    return this.getErrorMessage(exc, INTERNAL_SERVER_ERROR);
  }

  /**
   * @param exc exception that was thrown
   * @return error response for general exceptions with 400 code
   */
  @ExceptionHandler({InvalidParamException.class})
  public ResponseEntity<ErrorDto> handleInvalidParamException(Exception exc) {
    return this.getErrorMessage(exc, BAD_REQUEST);
  }

  /**
   * @param exc exception that was thrown
   * @return error response for general exceptions with 404 code
   */
  @ExceptionHandler({DataNotFoundException.class, UsernameNotFoundException.class})
  public ResponseEntity<ErrorDto> handleDataNotFoundException(Exception exc) {
    return this.getErrorMessage(exc, NOT_FOUND);
  }

  /**
   * @param exc exception that was thrown
   * @return error response for general exceptions with 401 code
   */
  @ExceptionHandler({UnauthorizedException.class})
  public ResponseEntity<ErrorDto> handleUnauthorizedException(Exception exc) {
    return this.getErrorMessage(exc, UNAUTHORIZED);
  }

  /**
   * @param exc exception that was thrown
   * @return Error response for passed parameters with vague message
   */
  private ResponseEntity<ErrorDto> getErrorMessage(Exception exc, HttpStatus status) {
    var error = new ErrorDto();
    error.setMessage(exc.getMessage());
    error.setStatus(status.value());

    return new ResponseEntity<>(error, status);
  }

  private static class ErrorDto {
    private int status;
    private String message;

    public int getStatus() {
      return status;
    }

    public void setStatus(int status) {
      this.status = status;
    }

    public String getMessage() {
      return message;
    }

    public void setMessage(String message) {
      this.message = message;
    }

  }

}
