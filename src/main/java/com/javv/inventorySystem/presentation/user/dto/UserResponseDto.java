package com.javv.inventorySystem.presentation.user.dto;

import java.util.UUID;

public record UserResponseDto(
    UUID id,
    String username,
    String displayName,
    String role,
    String profilePicture,
    String email,
    String phoneNumber,
    boolean isActive,
    String status) {}

// public class UserResponseDto {
//   private UUID id;
//   private String username;
//   private String displayName;
//   private String role;
//   private String profilePicture;
//   private String email;
//   private String phoneNumber;
//   private boolean isActive;
//   private String status;
//
//   public UserResponseDto() {}
//
//   public UserResponseDto(
//       UUID id,
//       String username,
//       String displayName,
//       String role,
//       String profilePicture,
//       String email,
//       String phoneNumber,
//       boolean isActive,
//       String status) {
//     this.id = id;
//     this.username = username;
//     this.displayName = displayName;
//     this.role = role;
//     this.profilePicture = profilePicture;
//     this.email = email;
//     this.phoneNumber = phoneNumber;
//     this.isActive = isActive;
//     this.status = status;
//   }
//
//   public void setId(UUID id) {
//     this.id = id;
//   }
//
//   public void setUsername(String username) {
//     this.username = username;
//   }
//
//   public void setDisplayName(String displayName) {
//     this.displayName = displayName;
//   }
//
//   public void setRole(String role) {
//     this.role = role;
//   }
//
//   public void setProfilePicture(String profilePicture) {
//     this.profilePicture = profilePicture;
//   }
//
//   public void setEmail(String email) {
//     this.email = email;
//   }
//
//   public void setPhoneNumber(String phoneNumber) {
//     this.phoneNumber = phoneNumber;
//   }
//
//   public void setIsActive(boolean isActive) {
//     this.isActive = isActive;
//   }
//
//   public void setStatus(String status) {
//     this.status = status;
//   }
//
//   public UUID getId() {
//     return id;
//   }
//
//   public String getUsername() {
//     return username;
//   }
//
//   public String getDisplayName() {
//     return displayName;
//   }
//
//   public String getRole() {
//     return role;
//   }
//
//   public String getProfilePicture() {
//     return profilePicture;
//   }
//
//   public String getEmail() {
//     return email;
//   }
//
//   public String getPhoneNumber() {
//     return phoneNumber;
//   }
//
//   public boolean getIsActive() {
//     return isActive;
//   }
//
//   public String getStatus() {
//     return status;
//   }
