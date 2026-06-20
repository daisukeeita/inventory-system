package com.javv.inventorySystem.application.command.transaction.inbound;

import com.javv.inventorySystem.domain.exception.RecordInitializationException;

public record InboundItemRegisterCommand(
    String productSku,
    String packagingCode,
    Integer quantityReceived) {

  public InboundItemRegisterCommand {

    if (productSku == null || productSku.isBlank()) {
      throw new RecordInitializationException(
          "Inbound Item Register Command: Product SKU cannot be null or empty.");
    }

    if (packagingCode == null || packagingCode.isBlank()) {
      throw new RecordInitializationException(
          "Inbound Item Register Command: Packaging Code cannot be null or empty.");
    }

    if (quantityReceived == null) {
      throw new RecordInitializationException(
          "Inbound Item Register Command: Quantity Received cannot be null.");
    }

    if (quantityReceived < 0) {
      throw new IllegalArgumentException(
          "Inbound Item Register Command: Quantity Received cannot be less than to zero.");
    }
  }
}
