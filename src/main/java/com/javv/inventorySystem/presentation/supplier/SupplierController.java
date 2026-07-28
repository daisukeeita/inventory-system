package com.javv.inventorySystem.presentation.supplier;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.javv.inventorySystem.application.command.supplier.SupplierRegisterCommand;
import com.javv.inventorySystem.application.command.supplier.SupplierUpdateCommand;
import com.javv.inventorySystem.application.service.supplier.SupplierService;
import com.javv.inventorySystem.domain.model.supplier.Supplier;
import com.javv.inventorySystem.presentation.shared.payload.ApiResponse;
import com.javv.inventorySystem.presentation.supplier.dto.SupplierRegisterDto;
import com.javv.inventorySystem.presentation.supplier.dto.SupplierResponseDto;
import com.javv.inventorySystem.presentation.supplier.dto.SupplierUpdateDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@RestController
@RequestMapping(value = "api/v1/supplier")
@Validated
public class SupplierController {

  private SupplierService supplierService;
  private SupplierDtoMapper supplierDtoMapper;

  public SupplierController(SupplierService supplierService, SupplierDtoMapper supplierDtoMapper) {
    this.supplierService = supplierService;
    this.supplierDtoMapper = supplierDtoMapper;
  }

  @PostMapping("/create")
  @ResponseStatus(HttpStatus.CREATED)
  public ApiResponse<SupplierResponseDto> registerSupplier(
      @Valid @RequestBody SupplierRegisterDto supplierRegisterDto) {

    SupplierRegisterCommand supplierRegisterCommand = supplierDtoMapper.toRegisterCommandRecord(supplierRegisterDto);

    Supplier supplier = supplierService.create(supplierRegisterCommand);

    SupplierResponseDto supplierResponseDto = supplierDtoMapper.toResponseDto(supplier);

    return ApiResponse.success(
        supplierResponseDto, "Successfully created new Supplier.", HttpStatus.CREATED.value());
  }

  @PutMapping("update/{supplierCode}")
  @ResponseStatus(HttpStatus.OK)
  public ApiResponse<SupplierResponseDto> updateSupplier(
      @PathVariable @NotBlank(message = "Query Parameter: Supplier Code cannot be blank") @Pattern(regexp = "^SUP-\\d{4,5}$", message = "Query Parameter: Supplier Code must match format SUP-xxxx.") String supplierCode,
      @Valid @RequestBody SupplierUpdateDto supplierUpdateDto) {

    SupplierUpdateCommand supplierUpdateCommand = supplierDtoMapper
        .toUpdateCommandRecord(supplierUpdateDto);

    Supplier supplier = supplierService.update(supplierCode, supplierUpdateCommand);

    SupplierResponseDto supplierResponseDto = supplierDtoMapper.toResponseDto(supplier);

    return ApiResponse.success(
        supplierResponseDto, "Successfully updated the Supplier", HttpStatus.OK.value());
  }

  @GetMapping("getAllSupplierCode")
  @ResponseStatus(HttpStatus.FOUND)
  public ApiResponse<List<String>> getAllSupplierCode() {
    List<String> listOfSupplierCode = supplierService.getAllSupplierCode();

    return ApiResponse.success(
        listOfSupplierCode,
        "Successfully found the list.",
        HttpStatus.FOUND.value());
  }
}
