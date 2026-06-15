package com.javv.inventorySystem.presentation.productPackaging.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

// @formatter:off
public record ProductPackagingUpdateDto(
    @NotNull(
      message = "Product Packaging Update DTO: ID is required for update.")
    @Positive(
      message = "Product Packaging Update DTO: ID must be a valid positive ID.")
    Long id,

    @NotBlank(
      message = "Product Packaging Update DTO: Packaging Code is required for update.")
    @Size(
      max = 20,
      message = "Product Packaging Update DTO: Packaging Code must not exceed 50 characters.")
    String packagingCode,

    @NotNull(
      message = "Product Packaging Update DTO: Product ID is required for update.")
    @Positive(
      message = "Product Packaging Update DTO: Product ID must be a valid positive ID.")
    Long productId,

    @NotNull(
      message = "Product Packaging Update DTO: Unit of Measure ID is required for update.")
    @Positive(
      message = "Product Packaging Update DTO: Unit of Measure ID must be a valid positive ID."
    )
    Integer unitsOfMeasureId,

    @NotNull(
      message = "Product Packaging Update DTO: Conversion Factor is required for update.")
    @Positive(
      message = "Product Packaging Update DTO: Conversion Factor must be greater than zero.")
    Integer conversionFactor,

    @NotNull(
      message = "Product Packaging Update DTO: Price is required for update.")
    @Positive(
      message = "Product Packaging Update DTO: Price must be greater than zero.")
    @Digits(integer = 8, fraction = 2)
    BigDecimal price) {}
