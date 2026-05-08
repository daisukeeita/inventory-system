package com.javv.inventorySystem.presentation.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.javv.inventorySystem.domain.exception.EntityAlreadyExistsException;
import com.javv.inventorySystem.domain.exception.ResourceNotFoundException;
import com.javv.inventorySystem.domain.exception.SystemUnavailableException;
import com.javv.inventorySystem.presentation.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ApiResponse<Void>> handleResourceNotFoundException(
      ResourceNotFoundException exception) {

    ApiResponse<Void> response = ApiResponse.error(
        exception.getMessage(), HttpStatus.NOT_FOUND.value());

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
  }

  @ExceptionHandler(EntityAlreadyExistsException.class)
  public ResponseEntity<ApiResponse<Void>> handleEntityAlreadyExistsException(
      EntityAlreadyExistsException exception) {

    ApiResponse<Void> response = ApiResponse.error(
        exception.getMessage(), HttpStatus.CONFLICT.value());

    return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ApiResponse<Void>> handleDataConflict(
      DataIntegrityViolationException exception) {

    ApiResponse<Void> response = ApiResponse.error(
        "This Record was recently created by someone else.",
        HttpStatus.CONFLICT.value());

    return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
  }

  @ExceptionHandler(SystemUnavailableException.class)
  public ResponseEntity<ApiResponse<Void>> handleSystemUnavailableException(
      SystemUnavailableException exception) {

    ApiResponse<Void> response = ApiResponse.error(
        exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiResponse<Void>> handleGeneralException(Exception exception) {

    ApiResponse<Void> response = ApiResponse.error(
        "Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR.value());

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(response);
  }
}
