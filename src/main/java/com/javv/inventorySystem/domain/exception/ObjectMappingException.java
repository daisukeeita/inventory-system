package com.javv.inventorySystem.domain.exception;

public class ObjectMappingException extends RuntimeException {

  public ObjectMappingException(String message) {
    super(message);
  }

  public ObjectMappingException(String message, Throwable cause) {
    super(message, cause);
  }
}
