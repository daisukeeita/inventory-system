package com.javv.inventorySystem.application.command.supplier;

import com.javv.inventorySystem.domain.exception.RecordInitializationException;

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

    if (companyName == null || companyName.isBlank()) {
      throw new RecordInitializationException(
          "Supplier Register Command: Company Name cannot be null or empty.");
    }

    if (contactName == null || contactName.isBlank()) {
      throw new RecordInitializationException(
          "Supplier Register Command: Contact Name cannot be null or empty.");
    }

    if (phoneNumber == null || phoneNumber.isBlank()) {
      throw new RecordInitializationException(
          "Supplier Register Command: Phone number cannot be null or empty.");
    }

    if (!phoneNumber.matches("^(?=^.{11,16}$)(0[2-9]\\d{0,2}|\\(0[2-9]\\d{0,2}\\))?[0-9\\-\\s]+$")) {
      throw new RecordInitializationException(
          "Supplier Register Command: Invalid PH phone number format. Total length (including spaces, dashes, parentheses) must be 11-16 characters.");
    }

    if (email == null || email.isBlank()) {
      throw new RecordInitializationException(
          "Supplier Register Command: Email cannot be null or empty.");
    }

    if (!email.contains("@")) {
      throw new RecordInitializationException(
          "Supplier Register Command: Invalid email format for command processing.");
    }

    if (street == null || street.isBlank()) {
      throw new RecordInitializationException(
          "Supplier Register Command: Street cannot be null or empty.");
    }

    if (city == null || city.isBlank()) {
      throw new RecordInitializationException(
          "Supplier Register Command: City cannot be null or empty.");
    }

    if (state == null || state.isBlank()) {
      throw new RecordInitializationException(
          "Supplier Register Command: State cannot be null or empty.");
    }

    if (postalCode == null || postalCode.isBlank()) {
      throw new RecordInitializationException(
          "Supplier Register Command: Postal Code cannot be null or empty.");
    }

    if (!postalCode.matches("^\\d{4}$")) {
      throw new IllegalArgumentException(
          "Supplier Register Command: Invalid PH Postal Code. It must be exactly 4 digits.");
    }

    if (country == null || country.isBlank()) {
      throw new RecordInitializationException(
          "Supplier Register Command: Country cannot be null or empty.");
    }
  }
}
