package com.javv.inventorySystem.application.dto.user;

public class UserCreateDto {
  private String username;
  private String password;
  private String roleName;
  private String status;
  private String firstName;
  private String middleInitial;
  private String lastName;
  private String profilePicture;
  private String email;
  private String phoneNumber;
  private String mailingAddress;

  public UserCreateDto() {
  }

  public UserCreateDto(
      final String username,
      final String password,
      final String roleName,
      final String status,
      final String firstName,
      final String middleInitial,
      final String lastName,
      final String profilePicture,
      final String email,
      final String phoneNumber,
      final String mailingAddress) {
    this.username = username;
    this.password = password;
    this.roleName = roleName;
    this.status = status;
    this.firstName = firstName;
    this.middleInitial = middleInitial;
    this.lastName = lastName;
    this.profilePicture = profilePicture;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.mailingAddress = mailingAddress;
  }

  public final void setUsername(final String username) {
    if (username == null || username.trim().isBlank()) {
      throw new IllegalArgumentException("Username is empty.");
    }
    this.username = username;
  }

  public final void setPassword(final String password) {
    if (password == null || password.trim().isBlank()) {
      throw new IllegalArgumentException("Password is empty.");
    }
    this.password = password;
  }

  public final void setRoleName(final String roleName) {
    if (roleName == null || roleName.trim().isBlank()) {
      throw new IllegalArgumentException("Role Name is empty.");
    }
    this.roleName = roleName;
  }

  public final void setUserStatus(final String status) {
    if (status == null || status.trim().isBlank()) {
      throw new IllegalArgumentException("Status is empty.");
    }
    this.status = status;
  }

  public final void setFirstName(final String firstName) {
    if (firstName == null || firstName.trim().isBlank()) {
      throw new IllegalArgumentException("First Name is empty.");
    }
    this.firstName = firstName;
  }

  public final void setMiddleInitial(final String middleInitial) {
    this.middleInitial = middleInitial;
  }

  public final void setLastName(final String lastName) {
    if (lastName == null || lastName.trim().isBlank()) {
      throw new IllegalArgumentException("Last Name is empty.");
    }
    this.lastName = lastName;
  }

  public final void setProfilePicture(final String profilePicture) {
    this.profilePicture = profilePicture;
  }

  public final void setEmail(final String email) {
    this.email = email;
  }

  public final void setPhoneNumber(final String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public final void setMailingAddress(final String mailingAddress) {
    this.mailingAddress = mailingAddress;
  }

  public final String getUsername() {
    return username;
  }

  public final String getPassword() {
    return password;
  }

  public final String getRoleName() {
    return roleName;
  }

  public final String getUserStatus() {
    return status;
  }

  public final String getFirstName() {
    return firstName;
  }

  public final String getMiddleInitial() {
    return middleInitial;
  }

  public final String getLastName() {
    return lastName;
  }

  public final String getProfilePicture() {
    return profilePicture;
  }

  public final String getEmail() {
    return email;
  }

  public final String getPhoneNumber() {
    return phoneNumber;
  }

  public final String getMailingAddress() {
    return mailingAddress;
  }
}
