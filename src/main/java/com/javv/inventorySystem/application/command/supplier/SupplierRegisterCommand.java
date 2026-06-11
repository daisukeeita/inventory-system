package com.javv.inventorySystem.application.command.supplier;

import java.util.Objects;

public record SupplierRegisterCommand(
    String companyName,
    String contactName,
    String phoneNumber,
    String email,
    String street,
    String city,
    String state,
    String postalCode,
    String country) {

  public SupplierRegisterCommand {
    Objects.requireNonNull(
        companyName,
        "Supplier Register Command Record: Company Name cannot be null.");

    Objects.requireNonNull(
        contactName,
        "Supplier Register Command Record: Contact Name cannot be null.");

    Objects.requireNonNull(
        phoneNumber,
        "Supplier Register Command Record: Phone Number cannot be null.");

    Objects.requireNonNull(
        email,
        "Supplier Register Command Record: Email cannot be null.");

    Objects.requireNonNull(
        street,
        "Supplier Register Command Record: Street cannot be null.");

    Objects.requireNonNull(
        city,
        "Supplier Register Command Record: City cannot be null.");

    Objects.requireNonNull(
        state,
        "Supplier Register Command Record: State cannot be null.");

    Objects.requireNonNull(
        postalCode,
        "Supplier Register Command Record: Postal Code cannot be null.");

    Objects.requireNonNull(
        country,
        "Supplier Register Command Record: Country cannot be null.");

    if (companyName.isBlank()) {
      throw new IllegalArgumentException(
          "Supplier Register Command: Company Name cannot be blank.");
    }

    if (contactName.isBlank()) {
      throw new IllegalArgumentException(
          "Supplier Register Command: Contact Name cannot be blank.");
    }

    if (phoneNumber.isBlank()) {
      throw new IllegalArgumentException(
          "Supplier Register Command: Phone Number cannot be blank.");
    }

    if (!phoneNumber.matches("^(0[2-9]\\d{0,2}|\\(0[2-9]\\d{0,2}\\))?[0-9\\-\\s]+$")) {
      throw new IllegalArgumentException(
          "Supplier Register Command: Invalid PH phone number format. Use digits, dashes, or parenthesis.");
    }

    if (!phoneNumber.matches("^\\d{10,15}$")) {
      throw new IllegalArgumentException(
          "Supplier Register Command: Phone Number must be between 10 and 15 digits.");
    }

    if (email.isBlank() || !email.contains("@")) {
      throw new IllegalArgumentException(
          "Supplier Register Command Record: Invalid email format for command processing.");
    }

    if (street.isBlank()) {
      throw new IllegalArgumentException(
          "Supplier Register Command: Street cannot be empty.");
    }

    if (city.isBlank()) {
      throw new IllegalArgumentException(
          "Supplier Register Command: City cannot be empty.");
    }

    if (state.isBlank()) {
      throw new IllegalArgumentException(
          "Supplier Register Command: State cannot be empty.");
    }

    if (postalCode.isBlank()) {
      throw new IllegalArgumentException(
          "Supplier Register Command: Postal Code cannot be empty.");
    }

    if (!postalCode.matches("^\\d{4}$")) {
      throw new IllegalArgumentException(
          "Supplier Register Command: Invalid PH Postal Code. It must be exactly 4 digits.");
    }

    if (country.isBlank()) {
      throw new IllegalArgumentException(
          "Supplier Register Command: Country cannot be empty.");
    }
  }
}
