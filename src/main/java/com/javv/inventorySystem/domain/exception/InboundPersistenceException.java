package com.javv.inventorySystem.domain.exception;

public class InboundPersistenceException extends RuntimeException {

  public InboundPersistenceException(String message) {
    super(message);
  }

  public InboundPersistenceException(String message, Throwable cause) {
    super(message, cause);
  }
}
