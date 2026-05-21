package com.javv.inventorySystem.presentation.productPackaging.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductPackagingResponseDto(
    @NotNull(message = "Product Packaging Response DTO: ID is required for response.") Integer id,
    @NotBlank(message = "Product Packaging Response DTO: Packaging Code is required for response.")
        String packagingCode,
    @NotBlank(message = "Product Packaging Response DTO: Product SKU is required for response.")
        String sku,
    @NotBlank(message = "Product Packaging Response DTO: Product Name is required for response.")
        String productName,
    @NotBlank(message = "Product Packaging Response DTO: Unit of Measure is required for response.")
        String unitOfMeasure,
    @NotNull(message = "Product Packaging Respone DTO: Conversion Factor is required for response.")
        int conversionFactor,
    @NotNull(message = "Product Packaging Response DTO: Packaging Price is required for response.")
        BigDecimal price) {}
