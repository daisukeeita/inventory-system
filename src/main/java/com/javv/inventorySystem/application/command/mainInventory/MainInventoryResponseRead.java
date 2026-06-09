package com.javv.inventorySystem.application.command.mainInventory;

import java.util.Objects;

public record MainInventoryResponseRead(

    int id,

    String sku,

    String productName,

    int quantityOnHand,

    int reorderLevel

) {

  public MainInventoryResponseRead {

    Objects.requireNonNull(
        id,
        "Main Inventory Response Read: ID cannot be null.");

    Objects.requireNonNull(
        sku,
        "Main Inventory Response Read: Product SKU cannot be null.");

    Objects.requireNonNull(
        productName,
        "Main Inventory Response Read: Product Name cannot be null.");

    Objects.requireNonNull(
        quantityOnHand,
        "Main Inventory Response Read: Quantity on Hand cannot be null.");

    Objects.requireNonNull(
        reorderLevel,
        "Main Inventory Response Read: Reorder Level cannot be null.");

  }
}
