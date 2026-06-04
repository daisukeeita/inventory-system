package com.javv.inventorySystem.presentation.mainInventory;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.javv.inventorySystem.application.command.mainInventory.MainInventoryRegisterCommand;
import com.javv.inventorySystem.domain.model.mainInventory.MainInventory;
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
        mainInventoryRegisterDto.sku(),
        mainInventoryRegisterDto.quantityOnHand(),
        mainInventoryRegisterDto.reorderLevel());
  }

  public MainInventoryResponseDto toResponseDto(MainInventory mainInventory) {

    Objects.requireNonNull(
        mainInventory,
        "Main Inventory DTO Mapper: Cannot map a null MainInventory to a Response DTO.");

    return new MainInventoryResponseDto(
        mainInventory.getId(),
        mainInventory.getProductId(),
        mainInventory.getProductId(),
        mainInventory.getQuantityOnHand(),
        mainInventory.getReorderLevel(),
        mainInventory.getCreatedAt(),
        mainInventory.getUpdatedAt());
  }
}
