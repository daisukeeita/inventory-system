package com.javv.inventorySystem.application.dto;

import java.util.UUID;

public class UserResponseDto {
  private UUID id;
  private String username;
  private String displayName;
  private String role;
  private String profilePicture;

  public UserResponseDto() {
  }

  public UserResponseDto(UUID id, String username, String displayName, String role, String profilePicture) {
    this.id = id;
    this.username = username;
    this.displayName = displayName;
    this.role = role;
    this.profilePicture = profilePicture;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public void setProfilePicture(String profilePicture) {
    this.profilePicture = profilePicture;
  }

  public UUID getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getDisplayName() {
    return displayName;
  }

  public String getRole() {
    return role;
  }

  public String getProfilePicture() {
    return profilePicture;
  }
}
