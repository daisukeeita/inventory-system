package com.javv.inventorySystem.presentation.transaction;

import java.util.List;

import org.springframework.stereotype.Component;

import com.javv.inventorySystem.application.command.transaction.inbound.InboundItemRegisterCommand;
import com.javv.inventorySystem.application.command.transaction.inbound.InboundRegisterCommand;
import com.javv.inventorySystem.presentation.transaction.inbound.dto.InboundRegisterDto;

@Component
public class TransactionDtoMapper {

  public InboundRegisterCommand toInboundCommand(InboundRegisterDto inboundRegisterDto) {

    List<InboundItemRegisterCommand> listItem = inboundRegisterDto.listInboundItem()
        .stream()
        .map(item -> new InboundItemRegisterCommand(
            item.productId(),
            item.packagingId(),
            item.quantityReceived()))
        .toList();

    return new InboundRegisterCommand(
        inboundRegisterDto.supplierId(),
        inboundRegisterDto.encoderId(),
        inboundRegisterDto.invoiceNumber(),
        inboundRegisterDto.dateReceived(),
        listItem);
  }
}
