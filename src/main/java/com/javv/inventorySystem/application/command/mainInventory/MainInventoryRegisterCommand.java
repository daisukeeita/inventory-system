package com.javv.inventorySystem.application.command.mainInventory;

import java.util.Objects;

public record MainInventoryRegisterCommand(Long productId, int quantityOnHand, int reorderLevel) {
  public MainInventoryRegisterCommand {
    Objects.requireNonNull(
        productId,
        "Main Inventory Register Command: Product ID cannot be null.");

    Objects.requireNonNull(
        quantityOnHand,
        "Main Inventory Register Command: Quantity on Hand cannot be null.");
    Objects.requireNonNull(
        reorderLevel,
        "Main Inventory Register Command: Reorder Level cannot be null.");

    if (quantityOnHand < 0) {
      throw new IllegalArgumentException(
          "Main Inventory Register Command: Quantity on Hand cannot be less than zero.");
    }

    if (reorderLevel < 0) {
      throw new IllegalArgumentException(
          "Main Inventory Register Command: Reorder Level cannot be less than zero.");
    }
  }
}
