package com.javv.inventorySystem.presentation.supplier.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SupplierRegisterDto(
    @NotBlank(message = "Supplier Register DTO: Company Name is required.") String companyName,
    @NotBlank(message = "Supplier Register DTO: Contact Name is required.") String contactName,
    @NotBlank(message = "Supplier Register DTO: Phone Number is required.") @Size(max = 15, message = "Supplier Register DTO: Phone Number should only have 15 characters") String phoneNumber,
    @NotBlank(message = "Supplier Register DTO: Email is required") @Email(message = "Supplier Register DTO: Provided email is not valid.") String email,
    @NotBlank(message = "Supplier Register DTO: Street is required.") String street,
    @NotBlank(message = "Supplier Register DTO: City is required.") String city,
    @NotBlank(message = "Supplier Register DTO: State is required.") String state,
    @NotBlank(message = "Supplier Register DTO: Postal Code is required.") String postalCode,
    @NotBlank(message = "Supplier Register DTO: Country is required.") String country) {
}
