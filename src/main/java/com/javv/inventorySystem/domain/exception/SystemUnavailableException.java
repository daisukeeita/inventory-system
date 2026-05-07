package com.javv.inventorySystem.domain.exception;

public class SystemUnavailableException extends RuntimeException {
  private int statusCode;

  public SystemUnavailableException(String message) {
    super(message);
  }

  public SystemUnavailableException(String message, Throwable cause) {
    super(message, cause);
  }

  public SystemUnavailableException(String message, int statusCode) {
    super(message);
    this.statusCode = statusCode;
  }

  public int getStatusCode() {
    return statusCode;
  }
}
