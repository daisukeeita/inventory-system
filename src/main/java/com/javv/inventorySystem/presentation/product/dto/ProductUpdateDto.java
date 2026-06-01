package com.javv.inventorySystem.presentation.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// @formatter:off
public record ProductUpdateDto(
    @NotBlank(message = "Product Update DTO: SKU is required for update.") 
    String sku,

    @NotBlank(message = "Product Update DTO: Name is required for update.") 
    String name,

    @NotNull(message = "Product Update DTO: Supplier ID is required for update.") 
    int supplierId,

    @NotNull(message = "Product Update DTO: Base Units of Measure ID is required for update.") 
    int baseUnitOfMeasureId) {
}
