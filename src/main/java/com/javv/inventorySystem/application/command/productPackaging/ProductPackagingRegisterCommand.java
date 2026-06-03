package com.javv.inventorySystem.application.command.productPackaging;

import java.math.BigDecimal;
import java.util.Objects;

public record ProductPackagingRegisterCommand(
    String packagingCode,
    int unitOfMeasureId,
    int conversionFactor,
    BigDecimal price) {

  public ProductPackagingRegisterCommand {
    Objects.requireNonNull(
        packagingCode, "Product Packaging Register Command: Packaging Code cannot be null.");

    Objects.requireNonNull(
        unitOfMeasureId, "Product Packaging Register Command: Unit of Measure ID cannot be null.");

    Objects.requireNonNull(
        conversionFactor, "Product Packaging Register Command: Conversion Factor cannot be null.");

    Objects.requireNonNull(
        price, "Product Packaging Register Command: Packaging Price cannot be null.");

    if (conversionFactor <= 0) {
      throw new IllegalArgumentException(
          "Product Packaging Register Command: Conversion Factor cannot be less than or equal to 0."
              + " Provided: "
              + conversionFactor
              + ".");
    }

    if (price.compareTo(BigDecimal.ZERO) < 0) {
      throw new IllegalArgumentException(
          "Product Packaging Register Command: Price cannot be less than 0. Provided "
              + price
              + ".");
    }

    if (price.scale() > 2) {
      throw new IllegalArgumentException(
          "Product Packaging Register Command: Price cannot have more than 2 decimal places.");
    }
  }
}
