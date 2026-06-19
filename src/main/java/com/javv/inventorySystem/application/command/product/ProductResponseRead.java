package com.javv.inventorySystem.application.command.product;

import java.util.List;

import com.javv.inventorySystem.application.command.productPackaging.ProductPackagingResponseRead;
import com.javv.inventorySystem.domain.exception.RecordInitializationException;

public record ProductResponseRead(
    String sku,
    String name,
    String supplierCode,
    String supplierName,
    String baseUnitsOfMeasure,
    List<ProductPackagingResponseRead> listProductPackaging) {

  public ProductResponseRead {

    if (sku == null || sku.isBlank()) {
      throw new RecordInitializationException(
          "Product Response Read: SKU cannot be null or blank.");
    }

    if (name == null || name.isBlank()) {
      throw new RecordInitializationException(
          "Product Response Read: Name cannot be null or blank.");
    }

    if (supplierCode == null || supplierCode.isBlank()) {
      throw new RecordInitializationException(
          "Product Response Read: Supplier Code cannot be null or blank.");
    }

    if (supplierName == null || supplierName.isBlank()) {
      throw new RecordInitializationException(
          "Product Response Read: Supplier Name cannot be null or blank.");
    }

    if (baseUnitsOfMeasure == null || baseUnitsOfMeasure.isBlank()) {
      throw new RecordInitializationException(
          "Product Response Read: Base Unit of Measure cannot be null or blank.");
    }

    if (listProductPackaging == null || listProductPackaging.isEmpty()) {
      throw new RecordInitializationException(
          "Product Response Read: List of Packages cannot be null or empty.");
    }
  }
}
