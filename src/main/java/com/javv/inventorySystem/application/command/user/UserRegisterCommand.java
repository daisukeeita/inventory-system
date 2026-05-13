package com.javv.inventorySystem.application.command.user;

public record UserRegisterCommand(
    String username,
    String hashedPassword,
    String roleName,
    String firstName,
    String middleInitial,
    String lastName,
    String profilePicture,
    String email,
    String phoneNumber,
    String mailingAddress) {}
