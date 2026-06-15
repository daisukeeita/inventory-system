package com.javv.inventorySystem.presentation.mainInventory;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.javv.inventorySystem.application.command.mainInventory.MainInventoryRegisterCommand;
import com.javv.inventorySystem.application.command.mainInventory.MainInventoryResponseRead;
import com.javv.inventorySystem.application.service.mainInventory.MainInventoryService;
import com.javv.inventorySystem.presentation.mainInventory.dto.MainInventoryRegisterDto;
import com.javv.inventorySystem.presentation.mainInventory.dto.MainInventoryResponseDto;
import com.javv.inventorySystem.presentation.shared.payload.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/inventory")
public class MainInventoryController {

  private MainInventoryService mainInventoryService;
  private MainInventoryDtoMapper mainInventoryDtoMapper;

  public MainInventoryController(
      MainInventoryService mainInventoryService,
      MainInventoryDtoMapper mainInventoryDtoMapper) {
    this.mainInventoryService = mainInventoryService;
    this.mainInventoryDtoMapper = mainInventoryDtoMapper;
  }

  @PostMapping("register")
  @ResponseStatus(HttpStatus.CREATED)
  public ApiResponse<MainInventoryResponseDto> saveInventory(
      @Valid @RequestBody MainInventoryRegisterDto mainInventoryRegisterDto) {
    MainInventoryRegisterCommand registerCommand = mainInventoryDtoMapper.toRegisterCommand(mainInventoryRegisterDto);

    MainInventoryResponseRead mainInventoryResponseRead = mainInventoryService
        .create(registerCommand);

    MainInventoryResponseDto responseDto = mainInventoryDtoMapper
        .toResponseDto(mainInventoryResponseRead);

    return ApiResponse.success(
        responseDto,
        "Inventory successfully created.",
        HttpStatus.CREATED.value());
  }
}
