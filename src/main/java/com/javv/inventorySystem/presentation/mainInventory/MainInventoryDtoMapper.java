package com.javv.inventorySystem.presentation.mainInventory;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.javv.inventorySystem.application.command.mainInventory.MainInventoryRegisterCommand;
import com.javv.inventorySystem.application.command.mainInventory.MainInventoryResponseRead;
import com.javv.inventorySystem.presentation.mainInventory.dto.MainInventoryRegisterDto;
import com.javv.inventorySystem.presentation.mainInventory.dto.MainInventoryResponseDto;

@Component
public class MainInventoryDtoMapper {

  public MainInventoryRegisterCommand toRegisterCommand(
      MainInventoryRegisterDto mainInventoryRegisterDto) {

    Objects.requireNonNull(
        mainInventoryRegisterDto,
        "Main Inventory DTO Mapper: Cannot map a null MainInventoryRegisterDto to a Command"
            + " Record.");

    return new MainInventoryRegisterCommand(
        mainInventoryRegisterDto.productId(),
        mainInventoryRegisterDto.quantityOnHand(),
        mainInventoryRegisterDto.reorderLevel());
  }

  public MainInventoryResponseDto toResponseDto(
      MainInventoryResponseRead mainInventoryResponseRead) {

    Objects.requireNonNull(
        mainInventoryResponseRead,
        "Main Inventory DTO Mapper: Cannot map a null MainInventoryResponseRead to a Response DTO.");

    return new MainInventoryResponseDto(
        mainInventoryResponseRead.id(),
        mainInventoryResponseRead.sku(),
        mainInventoryResponseRead.productName(),
        mainInventoryResponseRead.quantityOnHand(),
        mainInventoryResponseRead.reorderLevel());
  }
}
