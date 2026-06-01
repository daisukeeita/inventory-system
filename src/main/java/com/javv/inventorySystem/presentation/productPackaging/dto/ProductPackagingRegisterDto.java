package com.javv.inventorySystem.presentation.productPackaging.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// @formatter:off
public record ProductPackagingRegisterDto(
    @NotBlank(
      message = "Product Packaging Register DTO: Packaging Code is required for registration.")
    String packagingCode,

    @NotNull(
      message = "Product Packaging Register DTO: Product ID is required for registration.")
    Long productId,

    @NotNull(
      message = "Product Packaging Register DTO: Unit of Measure ID is required for registration.")
    int unitsOfMeasureId,

    @NotNull(
      message = "Product Packaging Register DTO: Conversion Factor is required for registration.")
    int conversionFactor,

    @NotNull(
      message = "Product Packaging Register DTO: Price is required for registration.")
    BigDecimal price) {}
