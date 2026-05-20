package com.javv.inventorySystem.presentation.product.dto;

import jakarta.validation.constraints.NotBlank;

public record ProductRegisterDto(
    @NotBlank(message = "Product Register DTO: SKU is required.") String sku,

    @NotBlank(message = "Product Register DTO: Product Name is required.") String name,

    @NotBlank(message = "Product Register DTO: Supplier Name is required.") String supplier,

    @NotBlank(message = "Product Register DTO: Base Unit of Measure is required.") String baseUnitOfMeasure) {
}
