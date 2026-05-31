package com.javv.inventorySystem.application.command.transaction.inbound;

import java.util.Objects;

public record InboundItemRegisterCommand(
    String productSku,
    int packagingId,
    int quantityReceived) {

  public InboundItemRegisterCommand {

    Objects.requireNonNull(productSku,
        "Inbound Item Register Command: Product SKU cannot be null.");

    Objects.requireNonNull(packagingId,
        "Inbound Item Register Command: Packaging ID cannot be null.");

    Objects.requireNonNull(quantityReceived,
        "Inbound Item Register Command: Quantity Received cannot be null.");

    if (productSku.isBlank()) {
      throw new IllegalArgumentException(
          "Inbound Item Register Command: Product SKU cannot be empty.");
    }

    if (packagingId < 0) {
      throw new IllegalArgumentException(
          "Inbound Item Register Command: Packaging ID does not have less than or equal to zero ID number.");
    }

    if (quantityReceived < 0) {
      throw new IllegalArgumentException(
          "Inbound Item Register Command: Quantity Received cannot be less than or equal to zero.");
    }
  }
}
