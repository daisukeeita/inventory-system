package com.javv.inventorySystem.domain.exception;

public class EntityAlreadyExistsException extends RuntimeException {
  private int statusCode;

  public EntityAlreadyExistsException(String message) {
    super(message);
  }

  public EntityAlreadyExistsException(String message, Throwable cause) {
    super(message, cause);
  }

  public EntityAlreadyExistsException(String message, int statusCode) {
    super(message);
    this.statusCode = statusCode;
  }

  public int getStatusCode() {
    return statusCode;
  }
}
