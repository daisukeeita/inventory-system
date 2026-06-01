package com.javv.inventorySystem.application.command.product;

import java.util.List;
import java.util.Objects;

import com.javv.inventorySystem.application.command.productPackaging.ProductPackagingRegisterCommand;

public record ProductRegisterCommand(
    String sku,
    String name,
    int supplierId,
    int baseUnitOfMeasureId,
    List<ProductPackagingRegisterCommand> listPackaging) {

  public ProductRegisterCommand {

    Objects.requireNonNull(sku,
        "Product Register Command: SKU cannot be null");

    Objects.requireNonNull(name,
        "Product Register Command: Product Name cannot be null.");

    Objects.requireNonNull(supplierId,
        "Product Register Command: Supplier Name cannot be null.");

    Objects.requireNonNull(baseUnitOfMeasureId,
        "Product Register Command: Base Unit of Measure cannot be null.");
  }
}
