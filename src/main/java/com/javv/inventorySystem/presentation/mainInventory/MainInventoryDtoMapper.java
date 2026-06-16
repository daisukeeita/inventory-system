package com.javv.inventorySystem.presentation.mainInventory;

import org.springframework.stereotype.Component;

import com.javv.inventorySystem.application.command.mainInventory.MainInventoryRegisterCommand;
import com.javv.inventorySystem.application.command.mainInventory.MainInventoryResponseRead;
import com.javv.inventorySystem.domain.exception.ObjectMappingException;
import com.javv.inventorySystem.presentation.mainInventory.dto.MainInventoryRegisterDto;
import com.javv.inventorySystem.presentation.mainInventory.dto.MainInventoryResponseDto;

@Component
public class MainInventoryDtoMapper {

  public MainInventoryRegisterCommand toRegisterCommand(
      MainInventoryRegisterDto mainInventoryRegisterDto) {

    if (mainInventoryRegisterDto == null) {
      throw new ObjectMappingException(
          "Main Inventory DTO Mapper: Cannot map a null MainInventoryRegisterDTO to a Comman Record.");
    }

    return new MainInventoryRegisterCommand(
        mainInventoryRegisterDto.productSku(),
        mainInventoryRegisterDto.quantityOnHand(),
        mainInventoryRegisterDto.reorderLevel());
  }

  public MainInventoryResponseDto toResponseDto(
      MainInventoryResponseRead mainInventoryResponseRead) {

    if (mainInventoryResponseRead == null) {
      throw new ObjectMappingException(
          "Main Inventory DTO Mapper: Cannot map a null MainInventoryResponseRead to a Response DTO.");
    }

    return new MainInventoryResponseDto(
        mainInventoryResponseRead.id(),
        mainInventoryResponseRead.sku(),
        mainInventoryResponseRead.productName(),
        mainInventoryResponseRead.quantityOnHand(),
        mainInventoryResponseRead.reorderLevel());
  }
}
