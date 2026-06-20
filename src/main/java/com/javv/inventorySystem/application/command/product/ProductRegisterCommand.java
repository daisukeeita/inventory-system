package com.javv.inventorySystem.application.command.product;

import java.util.List;

import com.javv.inventorySystem.application.command.productPackaging.ProductPackagingRegisterCommand;
import com.javv.inventorySystem.domain.exception.RecordInitializationException;

public record ProductRegisterCommand(
    String sku,
    String name,
    String supplierCode,
    String baseUnitOfMeasure,
    List<ProductPackagingRegisterCommand> listPackagingCommand) {

  public ProductRegisterCommand {

    if (sku == null || sku.isBlank()) {
      throw new RecordInitializationException(
          "Product Register Command: SKU cannot be null or blank.");
    }

    if (name == null || name.isBlank()) {
      throw new RecordInitializationException(
          "Product Register Command: Name cannot be null or blank.");
    }

    if (supplierCode == null || supplierCode.isBlank()) {
      throw new RecordInitializationException(
          "Product Register Command: Supplier Code cannot be null or blank.");
    }

    if (baseUnitOfMeasure == null || baseUnitOfMeasure.isBlank()) {
      throw new RecordInitializationException(
          "Product Register Command: Base Unit of Measure Name cannot be null or blank.");
    }

    if (listPackagingCommand == null || listPackagingCommand.isEmpty()) {
      throw new RecordInitializationException(
          "Product Register Command: List of Packages cannot be null or empty.");
    }
  }
}
