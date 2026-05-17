package com.javv.inventorySystem.presentation.supplier.dto;

import jakarta.validation.constraints.NotBlank;

public record SupplierUpdateAddressDto(
    @NotBlank(message = "Street is required.") String street,
    @NotBlank(message = "City is required.") String city,
    @NotBlank(message = "State is required.") String state,
    @NotBlank(message = "Postal Code is required.") String postalCode,
    @NotBlank(message = "Country is required.") String country) {}
