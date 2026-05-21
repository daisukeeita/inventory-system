package com.javv.inventorySystem.application.command.productPackaging;

import java.math.BigDecimal;
import java.util.Objects;

public record ProductPackagingUpdateCommand(
    Integer id,
    String packagingCode,
    String sku,
    String unitOfMeasure,
    int conversionFactor,
    BigDecimal price) {

  public ProductPackagingUpdateCommand {
    Objects.requireNonNull(id, "Product Packaging Update Command: ID cannot be null.");

    Objects.requireNonNull(
        packagingCode, "Product Packaging Update Command: Packaging Code cannot be null.");

    Objects.requireNonNull(sku, "Product Packaging Update Command: SKU cannot be null.");

    Objects.requireNonNull(
        unitOfMeasure, "Product Packaging Update Command: Unit of Measure cannot be null.");

    Objects.requireNonNull(
        conversionFactor, "Product Packaging Update Command: Conversion Factor cannot be null.");

    Objects.requireNonNull(
        price, "Product Packaging Update Command: Packaging Price cannot be null.");

    if (conversionFactor <= 0) {
      throw new IllegalArgumentException(
          "Product Packaging Update Command: Conversion Factor cannot be less than or equal to 0."
              + " Provided: "
              + conversionFactor
              + ".");
    }

    if (price.compareTo(BigDecimal.ZERO) < 0) {
      throw new IllegalArgumentException(
          "Product Packaging Update Command: Price cannot be less than 0. Provided " + price + ".");
    }

    if (price.scale() > 2) {
      throw new IllegalArgumentException(
          "Product Packaging Update Command: Price cannot have more than 2 decimal places.");
    }
  }
}
