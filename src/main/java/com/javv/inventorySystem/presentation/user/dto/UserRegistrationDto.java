package com.javv.inventorySystem.presentation.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// @formatter:off
public record UserRegistrationDto(
    @NotBlank(message = "Username is required.")
    @Size(min = 3, max = 20, message = "Username must be at least 3 characters.")
    String username,

    @NotBlank(message = "Password is required.")
    @Size(min = 8, message = "Password must be at least 8 characters.")
    String password,

    @NotBlank(message = "Role is required.") 
    String roleName,

    @NotBlank(message = "Status is required.")
    String status,

    @NotBlank(message = "First Name is required.")
    String firstName,

    String middleInitial,

    @NotBlank(message = "Last Name is required.")
    String lastName,

    String profilePicture,

    @Email(message = "Email format is not valid.")
    @NotBlank(message = "Email is required.")
    String email,

    @NotBlank(message = "Phone Number is required.")
    @Size(max = 11, message = "Phone Number should only have 11 numbers.")
    String phoneNumber,

    String mailingAddress) {}
