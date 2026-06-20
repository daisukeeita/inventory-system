package com.javv.inventorySystem.presentation.productPackaging.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

// @formatter:off
public record ProductPackagingRegisterDto(
    @NotBlank(
      message = "Product Packaging Register DTO: Unit of Measure is required for registration.")
    String unitOfMeasure,

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
