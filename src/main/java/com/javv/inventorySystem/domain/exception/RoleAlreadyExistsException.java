package com.javv.inventorySystem.domain.exception;

public class RoleAlreadyExistsException extends RuntimeException {
  private int statusCode;

  public RoleAlreadyExistsException(String message) {
    super(message);
  }

  public RoleAlreadyExistsException(String message, Throwable cause) {
    super(message, cause);
  }

  public RoleAlreadyExistsException(String message, int statusCode) {
    super(message);
    this.statusCode = statusCode;
  }

  public int getStatusCode() {
    return statusCode;
  }
}
