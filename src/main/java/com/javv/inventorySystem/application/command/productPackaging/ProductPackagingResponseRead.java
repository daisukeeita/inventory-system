package com.javv.inventorySystem.application.command.productPackaging;

import java.math.BigDecimal;

import com.javv.inventorySystem.domain.exception.RecordInitializationException;

public record ProductPackagingResponseRead(
    String packagingCode,
    String sku,
    String productName,
    String unitOfMeasure,
    Integer conversionFactor,
    BigDecimal price) {

  public ProductPackagingResponseRead {

    if (packagingCode == null || packagingCode.isBlank()) {
      throw new RecordInitializationException(
          "Product Packaging Response Read: Packaging Code cannot be null or empty.");
    }

    if (sku == null || sku.isBlank()) {
      throw new RecordInitializationException(
          "Product Packaging Response Read: Product SKU cannot be null or empty.");
    }

    if (productName == null || productName.isBlank()) {
      throw new RecordInitializationException(
          "Product Packaging Response Read: Product Name cannot be null or empty.");
    }

    if (unitOfMeasure == null || unitOfMeasure.isBlank()) {
      throw new RecordInitializationException(
          "Product Packaging Response Read: Unit of Measure cannot be null or empty.");
    }

    if (conversionFactor == null) {
      throw new RecordInitializationException(
          "Product Packaging Response Read: Conversion Factor cannot be null or empty.");
    }

    if (price == null) {
      throw new RecordInitializationException(
          "Product Packaging Response Read: Price cannot be null or empty.");
    }
  }
}
