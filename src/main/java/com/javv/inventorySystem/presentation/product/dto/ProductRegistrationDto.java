package com.javv.inventorySystem.presentation.product.dto;

import jakarta.validation.constraints.NotBlank;

public record ProductRegistrationDto(
    @NotBlank(message = "SKU is required.") String sku,

    @NotBlank(message = "Product Name is required.") String name,

    @NotBlank(message = "Supplier Name is required.") String supplier,

    @NotBlank(message = "Base Unit of Measure is required.") String baseUnitOfMeasure) {
}
