package com.javv.inventorySystem.domain.model.user;

import java.time.LocalDate;
import java.util.UUID;

import com.javv.inventorySystem.domain.model.role.Role;

public class User {
  private UUID id;
  private String username;
  private String hashedPassword;
  private Role role;
  private PersonalDetails personalDetails;
  private ContactInformation contactInformation;
  private UserStatus status;
  private boolean isActive;
  private LocalDate createdAt;
  private LocalDate updatedAt;

  public User() {}

  public User(
      UUID id,
      String username,
      String hashedPassword,
      Role role,
      UserStatus status,
      PersonalDetails personalDetails,
      ContactInformation contactInformation) {
    this.id = id;
    this.username = username;
    this.hashedPassword = hashedPassword;
    this.role = role;
    this.status = status;
    this.personalDetails = personalDetails;
    this.contactInformation = contactInformation;
  }

  public void setId(final UUID id) {
    if (id == null) {
      throw new IllegalArgumentException("User ID should not be empty.");
    }
    this.id = id;
  }

  public void setUsername(final String username) {
    if (username == null || username.trim().isBlank()) {
      throw new IllegalArgumentException("Username should not be empty.");
    }
    this.username = username;
  }

  public void setHashedPassword(final String hashedPassword) {
    if (hashedPassword == null || hashedPassword.trim().isBlank()) {
      throw new IllegalArgumentException("Hashed Password should not be empty.");
    }
    this.hashedPassword = hashedPassword;
  }

  public void setRole(final Role role) {
    if (role == null) {
      throw new IllegalArgumentException("Role should not be empty.");
    }
    this.role = role;
  }

  public void setUserStatus(UserStatus status) {
    if (status == null) {
      throw new IllegalArgumentException("Status should not be empty.");
    }
    this.status = status;
  }

  public void setPersonalDetails(final PersonalDetails personalDetails) {
    if (personalDetails == null) {
      throw new IllegalArgumentException("Personal Details should not be empty.");
    }
    this.personalDetails = personalDetails;
  }

  public void setContactInformation(final ContactInformation contactInformation) {
    if (contactInformation == null) {
      throw new IllegalArgumentException("Contact Information should not be empty.");
    }
    this.contactInformation = contactInformation;
  }

  public void setCreatedAt(final LocalDate date) {
    if (date == null) {
      throw new IllegalArgumentException("Created At Date should not be empty.");
    }
    this.createdAt = date;
  }

  public void setUpdatedAt(final LocalDate date) {
    if (date == null) {
      throw new IllegalArgumentException("Updated At Date should not be empty.");
    }
    this.updatedAt = date;
  }

  public void setIsActive(final boolean isActive) {
    this.isActive = isActive;
  }

  public UUID getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getHashedPassword() {
    return hashedPassword;
  }

  public Role getRole() {
    return role;
  }

  public UserStatus getUserStatus() {
    return status;
  }

  public PersonalDetails getPersonalDetails() {
    return personalDetails;
  }

  public ContactInformation getContactInformation() {
    return contactInformation;
  }

  public boolean getIsActive() {
    return isActive;
  }

  public LocalDate getCreatedAt() {
    return createdAt;
  }

  public LocalDate getUpdatedAt() {
    return updatedAt;
  }

  /** Activating the user. */
  public void activateUser() {
    this.isActive = true;
  }

  /** Deactivating the user. */
  public void deactivateUser() {
    this.isActive = false;
  }
}
