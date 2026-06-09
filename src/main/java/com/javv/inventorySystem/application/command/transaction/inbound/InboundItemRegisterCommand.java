package com.javv.inventorySystem.application.command.transaction.inbound;

import java.util.Objects;

public record InboundItemRegisterCommand(
    Long productId,
    Long packagingId,
    int quantityReceived) {

  public InboundItemRegisterCommand {

    Objects.requireNonNull(productId,
        "Inbound Item Register Command: Product ID cannot be null.");
    if (productId <= 0) {
      throw new IllegalArgumentException(
          "Inbound Item Register Command: Product ID must be greater than zero.");
    }

    Objects.requireNonNull(packagingId,
        "Inbound Item Register Command: Packaging ID cannot be null.");
    if (packagingId <= 0) {
      throw new IllegalArgumentException(
          "Inbound Item Register Command: Packaging ID must be greater than zero.");
    }

    if (quantityReceived <= 0) {
      throw new IllegalArgumentException(
          "Inbound Item Register Command: Quantity Received cannot be less than or equal to zero.");
    }
  }
}
