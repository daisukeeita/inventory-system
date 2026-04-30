package com.javv.inventorySystem.domain.model.user;

import java.time.LocalDate;
import java.util.UUID;

public class User {
  private UUID id;
  private String username;
  private String hashedPassword;
  private Role role;
  private PersonalDetails personalDetails;
  private boolean isActive;
  private LocalDate createdAt;
  private LocalDate updatedAt;

  public User(
      UUID id, String username, String hashedPassword, Role role, PersonalDetails personalDetails) {
    this.id = id;
    this.username = username;
    this.hashedPassword = hashedPassword;
    this.role = role;
    this.personalDetails = personalDetails;
  }

  public void setId(final UUID id) {
    this.id = id;
  }

  public void setUsername(final String username) {
    this.username = username;
  }

  public void setHashedPassword(final String hashedPassword) {
    this.hashedPassword = hashedPassword;
  }

  public void setRole(final Role role) {
    this.role = role;
  }

  public void setPersonalDetails(final PersonalDetails personalDetails) {
    this.personalDetails = personalDetails;
  }

  public void setCreatedAt(final LocalDate date) {
    this.createdAt = date;
  }

  public void setUpdatedAt(final LocalDate date) {
    this.updatedAt = date;
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

  public PersonalDetails getPersonalDetails() {
    return personalDetails;
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

  /**
   * Activating the user.
   */
  public void activateUser() {
    this.isActive = true;
  }

  /**
   * Deactivating the user.
   */
  public void deactivateUser() {
    this.isActive = false;
  }

}
