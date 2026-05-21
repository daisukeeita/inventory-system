package com.javv.inventorySystem.presentation.productPackaging.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductPackagingUpdateDto(
    @NotBlank(message = "Product Packaging Update DTO: ID is required for update.") Integer id,
    @NotBlank(message = "Product Packaging Update DTO: Packaging Code is required for update.")
        String packagingCode,
    @NotBlank(message = "Product Packaging Update DTO: SKU is required for update.") String sku,
    @NotBlank(message = "Product Packaging Update DTO: Unit of Measure is required for update.")
        String unitOfMeasure,
    @NotNull(message = "Product Packaging Update DTO: Conversion Factor is required for update.")
        int conversionFactor,
    @NotNull(message = "Product Packaging Update DTO: Price is required for update.")
        BigDecimal price) {}
