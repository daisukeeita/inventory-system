package com.javv.inventorySystem.domain.model.user;

import java.util.UUID;

public class PersonalDetails {
  private UUID userId;
  private String displayName;
  private String firstName;
  private String middleInitial;
  private String lastName;
  private String profilePicture;

  public PersonalDetails(
      UUID userId,
      String firstName,
      String middleInitial,
      String lastName,
      String profilePicture) {
    this.userId = userId;
    this.firstName = firstName;
    this.middleInitial = middleInitial;
    this.lastName = lastName;
    this.profilePicture = profilePicture;
  }

  public void setUserId(UUID userId) {
    this.userId = userId;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setMiddleInitial(String middleInitial) {
    this.middleInitial = middleInitial;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setProfilePicture(String profilePicture) {
    this.profilePicture = profilePicture;
  }

  public void setDisplayName() {
    this.displayName = firstName + " " + middleInitial + " " + lastName;
  }

  public UUID getUserId() {
    return userId;
  }

  public String getDisplayName() {
    return displayName;
  }

  public String getProfilePicture() {
    return profilePicture;
  }
}
