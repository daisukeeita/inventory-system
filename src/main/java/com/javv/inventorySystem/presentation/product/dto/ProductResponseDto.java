package com.javv.inventorySystem.presentation.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductResponseDto(
    @NotNull(message = "Product Response DTO: ID is required for response.") Long id,

    @NotBlank(message = "Product Response DTO: SKU is required for response.") String sku,

    @NotBlank(message = "Product Response DTO: Product Name is required for response.") String name,

    @NotBlank(message = "Product Response DTO: Supplier Name is required for response.") String supplier,

    @NotBlank(message = "Product Response DTO: Base Unit of Measure is required for response.") String baseUnitOfMeasure) {
}
