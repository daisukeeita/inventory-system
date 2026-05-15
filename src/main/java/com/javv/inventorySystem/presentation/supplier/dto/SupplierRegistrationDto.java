package com.javv.inventorySystem.presentation.supplier.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SupplierRegistrationDto(
    @NotBlank(message = "Company Name is required.") String companyName,
    @NotBlank(message = "Contact Name is required.") String contactName,
    @NotBlank(message = "Phone Number is required.") @Size(max = 15, message = "Phone Number should only have 15 characters") String phoneNumber,
    @NotBlank(message = "Email is required") @Email(message = "Provided email is not valid.") String email,
    @NotBlank(message = "Street is required.") String street,
    @NotBlank(message = "City is required.") String city,
    @NotBlank(message = "State is required.") String state,
    @NotBlank(message = "Postal Code is required.") String postalCode,
    @NotBlank(message = "Country is required.") String country) {
}
