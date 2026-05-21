package com.javv.inventorySystem.application.command.product;

import java.util.Objects;

public record ProductUpdateCommand(
    String sku, String name, String supplier, String baseUnitOfMeasure) {

  public ProductUpdateCommand {

    Objects.requireNonNull(sku,
        "Product Update Command Record: SKU cannot be null.");

    Objects.requireNonNull(name,
        "Product Update Command Record: Name cannot be null.");

    Objects.requireNonNull(supplier,
        "Product Update Command Record: Supplier Name cannot be null.");

    Objects.requireNonNull(baseUnitOfMeasure,
        "Product Update Command Record: Base Unit of Measure cannot be null.");
  }
}
