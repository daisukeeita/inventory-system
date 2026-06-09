package com.javv.inventorySystem.application.command.transaction.inbound;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public record InboundRegisterCommand(
    int supplierId,
    UUID encoderId,
    String invoiceNumber,
    LocalDateTime dateReceived,
    List<InboundItemRegisterCommand> listInboundItem) {

  public InboundRegisterCommand {
    if (supplierId <= 0) {
      throw new IllegalArgumentException(
          "Inbound Register Command: Supplier ID must be greater that zero.");
    }

    Objects.requireNonNull(encoderId,
        "Inbound Register Command: Username cannot be null.");

    Objects.requireNonNull(invoiceNumber,
        "Inbound Register Command: Invoice Number cannot be null.");
    if (invoiceNumber.isBlank()) {
      throw new IllegalArgumentException(
          "Inbound Register Command: Invoice Number cannot be empty.");
    }

    Objects.requireNonNull(dateReceived,
        "Inbound Register Command: Date Received cannot be null.");

    Objects.requireNonNull(listInboundItem,
        "Inbound Register Command: List Items cannot be null.");
    if (listInboundItem.isEmpty()) {
      throw new IllegalArgumentException(
          "Inbound Register Command: List Items cannot be empty.");
    }

    listInboundItem = List.copyOf(listInboundItem);
  }
}
