package com.javv.inventorySystem.application.command.transaction.inbound;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public record InboundRegisterCommand(
    String supplierName,
    String username,
    String invoiceNumber,
    LocalDateTime dateReceived,
    List<InboundItemRegisterCommand> listInboundItem) {

  public InboundRegisterCommand {
    Objects.requireNonNull(supplierName,
        "Inbound Register Commmand: Supplier Name cannot be null.");

    Objects.requireNonNull(username,
        "Inbound Register Command: Username cannot be null.");

    Objects.requireNonNull(invoiceNumber,
        "Inbound Register Command: Invoice Number cannot be null.");

    Objects.requireNonNull(dateReceived,
        "Inbound Register Command: Date Received cannot be null.");

    if (supplierName.isBlank()) {
      throw new IllegalArgumentException(
          "Inbound Register Command: Supplier Name cannot be empty.");
    }

    if (username.isBlank()) {
      throw new IllegalArgumentException(
          "Inbound Register Command: Username cannot be empty.");
    }

    if (invoiceNumber.isBlank()) {
      throw new IllegalArgumentException(
          "Inbound Register Command: Invoice Number cannot be empty.");
    }
  }
}
