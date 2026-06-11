package com.javv.inventorySystem.domain.exception;

public class ServiceOperationException extends RuntimeException {

  public ServiceOperationException(String messsage) {
    super(messsage);
  }

  public ServiceOperationException(String message, Throwable cause) {
    super(message, cause);
  }
}
