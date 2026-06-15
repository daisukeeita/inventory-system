package com.javv.inventorySystem.presentation.productPackaging.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

// @formatter:off
public record ProductPackagingRegisterDto(
    @NotBlank(
      message = "Product Packaging Register DTO: Packaging Code is required for registration.")
    @Size(
      max = 20,
      message = "Product Packaging Register DTO: Packaging Code must not exceed 50 characters.")
    String packagingCode,

    @NotNull(
      message = "Product Packaging Register DTO: Unit of Measure ID is required for registration.")
    @Positive(
      message = "Product Packaging Register DTO: Unit of Measure ID must be a valid positive ID."
    )
    Integer unitsOfMeasureId,

    @NotNull(
      message = "Product Packaging Register DTO: Conversion Factor is required for registration.")
    @Positive(
      message = "Product Packaging Register DTO: Conversion Factor must be greater than zero.")
    Integer conversionFactor,

    @NotNull(
      message = "Product Packaging Register DTO: Price is required for registration.")
    @Positive(
      message = "Product Packaging Register DTO: Price must be greater than zero.")
    @Digits(integer = 8, fraction = 2)
    BigDecimal price) {}
