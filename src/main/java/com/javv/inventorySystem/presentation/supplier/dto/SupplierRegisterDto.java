package com.javv.inventorySystem.presentation.supplier.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

// @formatter:off
public record SupplierRegisterDto(
    @NotBlank(message = "Supplier Register DTO: Company Name is required.") 
    String companyName,

    @NotBlank(message = "Supplier Register DTO: Contact Name is required.") 
    String contactName,

    @NotBlank(message = "Supplier Register DTO: Phone Number is required.") 
    @Size(min = 11, max = 15, message = "Supplier Register DTO: Phone Number must be between 11 and 15 characters.")
    @Pattern(
      regexp = "^(0[2-9]\\d{0,2}|\\(0[2-9]\\d{0,2}\\))?[0-9\\-\\s]+$", 
      message = "Supplier Register DTO: Invalid PH phone number format. Use digits, dashes, or parentheses."
    )
    String phoneNumber,

    @NotBlank(message = "Supplier Register DTO: Email is required") 
    @Email(message = "Supplier Register DTO: Provided email is not valid.") 
    String email,

    @NotBlank(message = "Supplier Register DTO: Street is required.") 
    String street,

    @NotBlank(message = "Supplier Register DTO: City is required.") 
    String city,

    @NotBlank(message = "Supplier Register DTO: State is required.") 
    String state,

    @NotBlank(message = "Supplier Register DTO: Postal Code is required.") 
    @Pattern(
      regexp = "^\\d{4}$",
      message = "Supplier Register DTO: Invalid PH Postal Code. It must be exactly 4 digits."
    )
    String postalCode,

    @NotBlank(message = "Supplier Register DTO: Country is required.") 
    String country) {
}
