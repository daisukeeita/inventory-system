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
    if (userId == null) {
      throw new IllegalArgumentException("User ID should not be empty.");
    }
    this.userId = userId;
  }

  public void setEmail(String email) {
    if (email == null || email.trim().isBlank()) {
      throw new IllegalArgumentException("User Email should not be empty.");
    }
    if (!email.contains("@")) {
      throw new IllegalArgumentException("User Email should be in a correct format.");
    }
    this.email = email;
  }

  public void setPhoneNumber(String phoneNumber) {
    if (phoneNumber == null || phoneNumber.trim().isBlank()) {
      throw new IllegalArgumentException("User Phone Number should not be empty.");
    }
    this.phoneNumber = phoneNumber;
  }

  public void setMailingAddress(String mailingAddress) {
    if (mailingAddress == null) {
      this.mailingAddress = "";
    }
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
