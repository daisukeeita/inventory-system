package com.javv.inventorySystem.domain.exception;

public class ResourceNotFoundException extends RuntimeException {
  private int statusCode;

  public ResourceNotFoundException(String message) {
    super(message);
  }

  public ResourceNotFoundException(String message, int statusCode) {
    super(message);
    this.statusCode = statusCode;
  }

  public ResourceNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public int getStatusCode() {
    return statusCode;
  }
}
