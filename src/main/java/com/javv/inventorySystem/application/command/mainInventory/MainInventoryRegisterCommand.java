package com.javv.inventorySystem.application.command.mainInventory;

import java.util.Objects;

public record MainInventoryRegisterCommand(String sku, int quantityOnHand, int reorderLevel) {
  public MainInventoryRegisterCommand {
    Objects.requireNonNull(sku, "Main Inventory Register Command: Product SKU cannot be null.");
    Objects.requireNonNull(
        quantityOnHand, "Main Inventory Register Command: Quantity on Hand cannot be null.");
    Objects.requireNonNull(
        reorderLevel, "Main Inventory Register Command: Reorder Level cannot be null.");

    if (quantityOnHand < 0) {
      throw new IllegalArgumentException(
          "Main Inventory Register Command: Quantity on Hand cannot be less than zero.");
    }

    if (reorderLevel < 0) {
      throw new IllegalArgumentException(
          "Main Inventory Register Command: Reorder Level cannot be less than zero.");
    }

    if (sku.isBlank()) {
      throw new IllegalArgumentException(
          "Main Inventory Register Command: Product SKU cannot be blank.");
    }
  }
}
