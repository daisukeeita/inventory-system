package com.javv.inventorySystem.presentation.productPackaging.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductPackagingRegisterDto(
    @NotBlank(
            message =
                "Product Packaging Register DTO: Packaging Code is required for registration.")
        String packagingCode,
    @NotBlank(message = "Product Packaging Register DTO: SKU is required for registration.")
        String sku,
    @NotBlank(
            message =
                "Product Packaging Register DTO: Unit of Measure is required for registration.")
        String unitsOfMeasure,
    @NotNull(
            message =
                "Product Packaging Register DTO: Conversion Factor is required for registration.")
        int conversionFactor,
    @NotNull(message = "Product Packaging Register DTO: Price is required for registration.")
        BigDecimal price) {}
