package com.javv.inventorySystem.presentation.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserLoginDto(
    @NotBlank(message = "Username is required.")
        @Size(min = 3, max = 20, message = "Username must be at least 3 characters.")
        String username,
    @NotBlank(message = "Password is required.")
        @Size(min = 8, message = "Password must be at least 8 characters.")
        String password) {}
