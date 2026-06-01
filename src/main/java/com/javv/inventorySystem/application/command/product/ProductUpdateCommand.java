package com.javv.inventorySystem.application.command.product;

import java.util.Objects;

public record ProductUpdateCommand(
    String sku,
    String name,
    int supplierId,
    int baseUnitOfMeasureId) {

  public ProductUpdateCommand {

    Objects.requireNonNull(sku,
        "Product Update Command Record: SKU cannot be null.");

    Objects.requireNonNull(name,
        "Product Update Command Record: Name cannot be null.");

    Objects.requireNonNull(supplierId,
        "Product Update Command Record: Supplier ID cannot be null.");

    Objects.requireNonNull(baseUnitOfMeasureId,
        "Product Update Command Record: Base Unit of Measure cannot be null.");
  }
}
