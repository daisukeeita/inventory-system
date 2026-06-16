package com.javv.inventorySystem.application.command.mainInventory;

import com.javv.inventorySystem.domain.exception.RecordInitializationException;

public record MainInventoryRegisterCommand(String productSku, Integer quantityOnHand, Integer reorderLevel) {
  public MainInventoryRegisterCommand {
    if (productSku == null || productSku.isBlank()) {
      throw new RecordInitializationException(
          "Main Inventory Register Command: Product SKU cannot be null or empty.");
    }

    if (quantityOnHand == null) {
      throw new RecordInitializationException(
          "Main Inventory Register Command: Quantity on Hand cannot be null.");
    }

    if (reorderLevel == null) {
      throw new RecordInitializationException(
          "Main Inventory Register Command: Reorder Level cannot be null.");
    }

    if (quantityOnHand < 0) {
      throw new RecordInitializationException(
          "Main Inventory Register Command: Quantity on Hand cannot be less than zero.");
    }

    if (reorderLevel < 0) {
      throw new RecordInitializationException(
          "Main Inventory Register Command: Reorder Level cannot be less than zero.");
    }
  }
}
