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

    if (email.isBlank() || !email.contains("@")) {
      throw new IllegalArgumentException(
          "Supplier Register Command Record: Invalid email format for command processing.");
    }
  }
}
