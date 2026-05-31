package com.javv.inventorySystem.presentation.transaction;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.javv.inventorySystem.application.command.transaction.inbound.InboundItemRegisterCommand;
import com.javv.inventorySystem.application.command.transaction.inbound.InboundRegisterCommand;
import com.javv.inventorySystem.presentation.transaction.inbound.dto.InboundRegisterDto;

@Component
public class TransactionDtoMapper {

  public InboundRegisterCommand toInboundCommand(InboundRegisterDto inboundRegisterDto) {

    List<InboundItemRegisterCommand> listItem = new ArrayList<InboundItemRegisterCommand>();

    inboundRegisterDto.listInboundItem().forEach(
        item -> listItem.add(new InboundItemRegisterCommand(
            item.productSku(),
            item.packagingId(),
            item.quantityReceived())));

    return new InboundRegisterCommand(
        inboundRegisterDto.supplierName(),
        inboundRegisterDto.username(),
        inboundRegisterDto.invoiceNumber(),
        inboundRegisterDto.dateReceived(),
        listItem);
  }
}
