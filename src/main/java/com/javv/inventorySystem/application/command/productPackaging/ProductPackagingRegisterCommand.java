package com.javv.inventorySystem.application.command.productPackaging;

import java.math.BigDecimal;

import com.javv.inventorySystem.domain.exception.RecordInitializationException;

public record ProductPackagingRegisterCommand(
    String unitOfMeasureName,
    Integer conversionFactor,
    BigDecimal price) {

  public ProductPackagingRegisterCommand {

    if (unitOfMeasureName == null || unitOfMeasureName.isBlank()) {
      throw new RecordInitializationException(
          "Product Packaging Register Command: Unit Of Measure cannot be null or blank.");
    }

    if (conversionFactor == null) {
      throw new RecordInitializationException(
          "Product Packaging Register Command: Conversion Factor cannot be null.");
    }

    if (price == null) {
      throw new RecordInitializationException(
          "Product Packaging Register Command: Price cannot be null.");
    }

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
