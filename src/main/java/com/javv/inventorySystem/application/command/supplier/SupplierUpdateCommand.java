package com.javv.inventorySystem.application.command.supplier;

import java.util.Objects;

public record SupplierUpdateCommand(
    String companyName,
    String contactName,
    String phoneNumber,
    String email,
    String street,
    String city,
    String state,
    String postalCode,
    String country) {

  public SupplierUpdateCommand {

    Objects.requireNonNull(
        companyName,
        "Supplier Update Command Record: Company Name cannot be null.");

    Objects.requireNonNull(
        contactName,
        "Supplier Update Command Record: Contact Name cannot be null.");

    Objects.requireNonNull(
        phoneNumber,
        "Supplier Update Command Record: Phone Number cannot be null.");

    Objects.requireNonNull(
        email,
        "Supplier Update Command Record: Email cannot be null.");

    Objects.requireNonNull(
        street,
        "Supplier Update Command Record: Street cannot be null.");

    Objects.requireNonNull(
        city,
        "Supplier Update Command Record: City cannot be null.");

    Objects.requireNonNull(
        state,
        "Supplier Update Command Record: State cannot be null.");

    Objects.requireNonNull(
        postalCode,
        "Supplier Update Command Record: Postal Code cannot be null.");

    Objects.requireNonNull(
        country,
        "Supplier Update Command Record: Country cannot be null.");

    if (email.isBlank() || !email.contains("@")) {
      throw new IllegalArgumentException(
          "Supplier Update Command Record: Invalid email format for command processing.");
    }
  }
}
