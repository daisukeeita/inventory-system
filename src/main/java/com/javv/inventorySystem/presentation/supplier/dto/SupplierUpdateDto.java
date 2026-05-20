package com.javv.inventorySystem.presentation.supplier.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SupplierUpdateDto(
    @NotBlank(message = "Supplier Update DTO: Company Name is required.") String companyName,
    @NotBlank(message = "Supplier Update DTO: Contact Name is required.") String contactName,
    @NotBlank(message = "Supplier Update DTO: Phone Number is required.") @Size(max = 15, message = "Supplier Register DTO: Phone Number should only have 15 characters") String phoneNumber,
    @NotBlank(message = "Supplier Update DTO: Email is required") @Email(message = "Supplier Register DTO: Provided email is not valid.") String email,
    @NotBlank(message = "Supplier Update DTO: Street is required.") String street,
    @NotBlank(message = "Supplier Update DTO: City is required.") String city,
    @NotBlank(message = "Supplier Update DTO: State is required.") String state,
    @NotBlank(message = "Supplier Update DTO: Postal Code is required.") String postalCode,
    @NotBlank(message = "Supplier Update DTO: Country is required.") String country) {
}
