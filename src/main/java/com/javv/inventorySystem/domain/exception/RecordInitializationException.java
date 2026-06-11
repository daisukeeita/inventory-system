package com.javv.inventorySystem.domain.exception;

public class RecordInitializationException extends RuntimeException {

  public RecordInitializationException(String message) {
    super(message);
  }

  public RecordInitializationException(String message, Throwable cause) {
    super(message, cause);
  }
}
