package com.javv.inventorySystem.presentation.product.dto;

import jakarta.validation.constraints.NotBlank;

public record ProductUpdateDto(
    @NotBlank(message = "Product Update DTO: SKU is required for update.") String sku,

    @NotBlank(message = "Product Update DTO: Name is required for update.") String name,

    @NotBlank(message = "Product Update DTO: Supplier Name is required for update.") String supplier,

    @NotBlank(message = "Product Update DTO: Base Units of Measure is required for update.") String baseUnitOfMeasure) {
}
