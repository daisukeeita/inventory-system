package com.javv.inventorySystem.presentation.supplier;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.javv.inventorySystem.application.command.supplier.SupplierRegisterCommand;
import com.javv.inventorySystem.application.service.supplier.SupplierService;
import com.javv.inventorySystem.domain.model.supplier.Supplier;
import com.javv.inventorySystem.presentation.shared.payload.ApiResponse;
import com.javv.inventorySystem.presentation.supplier.dto.SupplierRegistrationDto;
import com.javv.inventorySystem.presentation.supplier.dto.SupplierResponseDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "api/v1/supplier")
public class SupplierController {

  private SupplierService supplierService;
  private SupplierDtoMapper supplierDtoMapper;

  public SupplierController(
      SupplierService supplierService,
      SupplierDtoMapper supplierDtoMapper) {
    this.supplierService = supplierService;
    this.supplierDtoMapper = supplierDtoMapper;
  }

  @PostMapping("/register")
  @ResponseStatus(HttpStatus.CREATED)
  public ApiResponse<SupplierResponseDto> registerSupplier(
      @Valid @RequestBody SupplierRegistrationDto supplierRegistrationDto) {

    SupplierRegisterCommand supplierRegisterCommand = supplierDtoMapper
        .toCommandRecord(supplierRegistrationDto);

    Supplier supplier = supplierService.saveSupplier(supplierRegisterCommand);

    SupplierResponseDto supplierResponseDto = supplierDtoMapper.toResponseDto(supplier);

    return ApiResponse.success(
        supplierResponseDto,
        "Successfully created new Supplier.",
        HttpStatus.CREATED.value());
  }
}
