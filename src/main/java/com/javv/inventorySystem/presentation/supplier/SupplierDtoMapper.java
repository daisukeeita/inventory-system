package com.javv.inventorySystem.presentation.supplier;

import org.springframework.stereotype.Component;

import com.javv.inventorySystem.application.command.supplier.SupplierRegisterCommand;
import com.javv.inventorySystem.domain.model.supplier.Supplier;
import com.javv.inventorySystem.presentation.supplier.dto.SupplierRegistrationDto;
import com.javv.inventorySystem.presentation.supplier.dto.SupplierResponseDto;

@Component
public class SupplierDtoMapper {

  public SupplierDtoMapper() {
  }

  public SupplierRegisterCommand toCommandRecord(
      SupplierRegistrationDto supplierRegistrationDto) {

    return new SupplierRegisterCommand(
        supplierRegistrationDto.companyName(),
        supplierRegistrationDto.contactName(),
        supplierRegistrationDto.phoneNumber(),
        supplierRegistrationDto.email(),
        supplierRegistrationDto.street(),
        supplierRegistrationDto.city(),
        supplierRegistrationDto.state(),
        supplierRegistrationDto.postalCode(),
        supplierRegistrationDto.country());
  }

  public SupplierResponseDto toResponseDto(Supplier supplier) {
    String address = supplier.getSupplierAddress().getStreet() + ", "
        + supplier.getSupplierAddress().getCity() + ", "
        + supplier.getSupplierAddress().getState() + ", "
        + supplier.getSupplierAddress().getPostalCode() + ", "
        + supplier.getSupplierAddress().getCountry();

    return new SupplierResponseDto(
        supplier.getCompanyName(),
        supplier.getContactName(),
        supplier.getPhoneNumber(),
        supplier.getEmail(),
        address);
  }
}
