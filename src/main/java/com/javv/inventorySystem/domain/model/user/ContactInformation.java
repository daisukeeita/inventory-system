package com.javv.inventorySystem.domain.model.user;

import java.util.UUID;

public class ContactInformation {
  private UUID userId;
  private String email;
  private String phoneNumber;
  private String mailingAddress;

  public ContactInformation(
      UUID userId,
      String email,
      String phoneNumber,
      String mailingAddress) {
    this.userId = userId;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.mailingAddress = mailingAddress;
  }

  public void setUserId(UUID userId) {
    this.userId = userId;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public void setMailingAddress(String mailingAddress) {
    this.mailingAddress = mailingAddress;
  }

  public UUID getUserId() {
    return userId;
  }

  public String getEmail() {
    return email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public String getMailingAddress() {
    return mailingAddress;
  }
}
