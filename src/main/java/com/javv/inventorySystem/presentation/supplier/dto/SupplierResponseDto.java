package com.javv.inventorySystem.presentation.supplier.dto;

import jakarta.validation.constraints.NotBlank;

public record SupplierResponseDto(
    @NotBlank(message = "Supplier Response DTO: Supplier Code is required.") String supplierCode,

    @NotBlank(message = "Supplier Response DTO: Company Name is required.") String companyName,

    @NotBlank(message = "Supplier Response DTO: Contact Name is required.") String contactName,

    @NotBlank(message = "Supplier Response DTO: Phone Number is required.") String phoneNumber,

    @NotBlank(message = "Supplier Response DTO: Email is required.") String email,

    @NotBlank(message = "Supplier Response DTO: Address is required.") String address) {
}
