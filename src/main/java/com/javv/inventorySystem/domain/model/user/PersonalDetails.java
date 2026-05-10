package com.javv.inventorySystem.domain.model.user;

import java.util.UUID;

public class PersonalDetails {
  private UUID userId;
  private String displayName;
  private String firstName;
  private String middleInitial;
  private String lastName;
  private String profilePicture;

  public PersonalDetails() {}

  public PersonalDetails(
      UUID userId, String firstName, String middleInitial, String lastName, String profilePicture) {
    this.userId = userId;
    this.firstName = firstName;
    this.middleInitial = middleInitial;
    this.lastName = lastName;
    this.profilePicture = profilePicture;
  }

  public void setUserId(UUID userId) {
    if (userId == null) {
      throw new IllegalArgumentException("User ID should not be empty.");
    }
    this.userId = userId;
  }

  public void setFirstName(String firstName) {
    if (firstName == null || firstName.trim().isBlank()) {
      throw new IllegalArgumentException("User First Name should not be empty.");
    }
    this.firstName = firstName;
  }

  public void setMiddleInitial(String middleInitial) {
    if (middleInitial == null) {
      this.middleInitial = "";
    }
    this.middleInitial = middleInitial;
  }

  public void setLastName(String lastName) {
    if (lastName == null || lastName.trim().isBlank()) {
      throw new IllegalArgumentException("User Last Name should not be empty.");
    }
    this.lastName = lastName;
  }

  public void setProfilePicture(String profilePicture) {
    if (profilePicture == null) {
      profilePicture = "";
    }
    this.profilePicture = profilePicture;
  }

  public void setDisplayName() {
    this.displayName = firstName + " " + middleInitial + " " + lastName;
  }

  public void setFullName(String fullName) {
    this.displayName = fullName;
  }

  public UUID getUserId() {
    return userId;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getMiddleInitial() {
    return middleInitial;
  }

  public String getLastName() {
    return lastName;
  }

  public String getDisplayName() {
    return displayName;
  }

  public String getProfilePicture() {
    return profilePicture;
  }
}
