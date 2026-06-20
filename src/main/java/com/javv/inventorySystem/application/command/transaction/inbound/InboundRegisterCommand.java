package com.javv.inventorySystem.application.command.transaction.inbound;

import java.time.LocalDateTime;
import java.util.List;

import com.javv.inventorySystem.domain.exception.RecordInitializationException;

public record InboundRegisterCommand(
    String supplierCode,
    String encoderUsername,
    String invoiceNumber,
    LocalDateTime dateReceived,
    List<InboundItemRegisterCommand> listInboundItem) {

  public InboundRegisterCommand {
    if (supplierCode == null || supplierCode.isBlank()) {
      throw new RecordInitializationException(
          "Inbound Register Command: Supplier code cannot be null or empty.");
    }

    if (encoderUsername == null || encoderUsername.isBlank()) {
      throw new RecordInitializationException(
          "Inbound Register Command: Encoder Username cannot be null or empty.");
    }

    if (invoiceNumber == null || invoiceNumber.isBlank()) {
      throw new IllegalArgumentException(
          "Inbound Register Command: Invoice Number cannot be null or empty.");
    }

    if (listInboundItem.isEmpty()) {
      throw new IllegalArgumentException(
          "Inbound Register Command: List of Inbound Items cannot be empty.");
    }

    listInboundItem = List.copyOf(listInboundItem);
  }
}
