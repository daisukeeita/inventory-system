package com.javv.inventorySystem.application.dto.user;

public class UserLoginDto {
  private String username;
  private String plainPassword;

  public UserLoginDto() {
  }

  public UserLoginDto(String username, String plainPassword) {
    setUsername(username);
    setPlainPassword(plainPassword);
  }

  private void setUsername(String username) {
    this.username = username;
  }

  private void setPlainPassword(String plainPassword) {
    this.plainPassword = plainPassword;
  }

  public String getUsername() {
    return username;
  }

  public String getPlainPassword() {
    return plainPassword;
  }
}
