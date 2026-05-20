package com.javv.inventorySystem.application.command.product;

import java.util.Objects;

public record ProductRegisterCommand(
    String sku,
    String name,
    String supplier,
    String baseUnitOfMeasure) {

  public ProductRegisterCommand {

    Objects.requireNonNull(sku,
        "Product Register Command: SKU cannot be null");

    Objects.requireNonNull(name,
        "Product Register Command: Product Name cannot be null.");

    Objects.requireNonNull(supplier,
        "Product Register Command: Supplier Name cannot be null.");

    Objects.requireNonNull(baseUnitOfMeasure,
        "Product Register Command: Base Unit of Measure cannot be null.");
  }
}
