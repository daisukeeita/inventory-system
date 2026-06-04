package com.javv.inventorySystem.application.command.product;

import java.util.List;
import java.util.Objects;

import com.javv.inventorySystem.application.command.productPackaging.ProductPackagingResponseRead;

public record ProductResponseRead(
    Long id,
    String sku,
    String name,
    int supplierId,
    String supplierName,
    int baseUnitOfMeasureId,
    String baseUnitsOfMeasure,
    List<ProductPackagingResponseRead> listProductPackaging) {

  public ProductResponseRead {

    Objects.requireNonNull(id,
        "Product Response Read: ID cannot be null.");

    Objects.requireNonNull(sku,
        "Product Response Read: SKU cannot be null.");

    Objects.requireNonNull(name,
        "Product Response Read: Name cannot be null.");

    Objects.requireNonNull(supplierId,
        "Product Response Read: Supplier ID cannot be null.");

    Objects.requireNonNull(supplierName,
        "Product Response Read: Supplier Name cannot be null.");

    Objects.requireNonNull(baseUnitOfMeasureId,
        "Product Response Read: Base Unit of Measure ID cannot be null.");

    Objects.requireNonNull(baseUnitsOfMeasure,
        "Product Response Read: Base Units of Measure Name cannot be null.");

    Objects.requireNonNull(listProductPackaging,
        "Product Response Read: List of Product Packages cannot be null.");
  }
}
