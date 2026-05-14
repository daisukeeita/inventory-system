package com.javv.inventorySystem.presentation.shared.payload;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ApiResponse<T> {
  private boolean success;
  private String message;
  private T data;
  private int httpStatus;
  private String timestamp;

  private static LocalDateTime now = LocalDateTime.now();
  private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

  public static <T> ApiResponse<T> success(T data, String message, int httpStatus) {
    ApiResponse<T> response = new ApiResponse<>();
    response.setSuccess(true);
    response.setMessage(message);
    response.setData(data);
    response.setHttpStatus(httpStatus);
    response.setTimestamp(now.format(formatter));
    return response;
  }

  public static <T> ApiResponse<T> error(T data, String message, int httpStatus) {
    ApiResponse<T> response = new ApiResponse<>();
    response.setSuccess(false);
    response.setMessage(message);
    response.setData(data);
    response.setHttpStatus(httpStatus);
    response.setTimestamp(now.format(formatter));
    return response;
  }

  private void setSuccess(boolean success) {
    this.success = success;
  }

  private void setData(T data) {
    this.data = data;
  }

  private void setMessage(String message) {
    this.message = message;
  }

  private void setHttpStatus(int httpStatus) {
    this.httpStatus = httpStatus;
  }

  private void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }

  public boolean getSuccess() {
    return success;
  }

  public String getMessage() {
    return message;
  }

  public T getData() {
    return data;
  }

  public int getHttpStatus() {
    return httpStatus;
  }

  public String getTimestamp() {
    return timestamp;
  }
}
