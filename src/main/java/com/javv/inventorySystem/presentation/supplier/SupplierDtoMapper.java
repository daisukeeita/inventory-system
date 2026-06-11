package com.javv.inventorySystem.presentation.supplier;

import org.springframework.stereotype.Component;

import com.javv.inventorySystem.application.command.supplier.SupplierRegisterCommand;
import com.javv.inventorySystem.application.command.supplier.SupplierUpdateCommand;
import com.javv.inventorySystem.domain.exception.ObjectMappingException;
import com.javv.inventorySystem.domain.model.supplier.Supplier;
import com.javv.inventorySystem.presentation.supplier.dto.SupplierRegisterDto;
import com.javv.inventorySystem.presentation.supplier.dto.SupplierResponseDto;
import com.javv.inventorySystem.presentation.supplier.dto.SupplierUpdateDto;

@Component
public class SupplierDtoMapper {

  public SupplierRegisterCommand toRegisterCommandRecord(SupplierRegisterDto supplierRegisterDto) {

    if (supplierRegisterDto == null) {
      throw new ObjectMappingException(
          "Supplier DTO Mapper: Cannot map a null SupplierRegisterDto to a Command Record.");
    }

    return new SupplierRegisterCommand(
        supplierRegisterDto.companyName(),
        supplierRegisterDto.contactName(),
        supplierRegisterDto.phoneNumber(),
        supplierRegisterDto.email(),
        supplierRegisterDto.street(),
        supplierRegisterDto.city(),
        supplierRegisterDto.state(),
        supplierRegisterDto.postalCode(),
        supplierRegisterDto.country());
  }

  public SupplierUpdateCommand toUpdateCommandRecord(
      SupplierUpdateDto supplierUpdateDto) {

    if (supplierUpdateDto == null) {
      throw new ObjectMappingException(
          "Supplier DTO Mapper: Cannot map a null SupplierUpdateAddressDto to a Command Record.");
    }

    return new SupplierUpdateCommand(
        supplierUpdateDto.companyName(),
        supplierUpdateDto.contactName(),
        supplierUpdateDto.phoneNumber(),
        supplierUpdateDto.email(),
        supplierUpdateDto.street(),
        supplierUpdateDto.city(),
        supplierUpdateDto.state(),
        supplierUpdateDto.postalCode(),
        supplierUpdateDto.country());
  }

  public SupplierResponseDto toResponseDto(Supplier supplier) {

    if (supplier == null) {
      throw new ObjectMappingException(
          "Supplier DTO Mapper: Cannot map a null Supplier to a Response DTO.");
    }

    String address = supplier.getSupplierAddress().getStreet()
        + ", "
        + supplier.getSupplierAddress().getCity()
        + ", "
        + supplier.getSupplierAddress().getState()
        + ", "
        + supplier.getSupplierAddress().getPostalCode()
        + ", "
        + supplier.getSupplierAddress().getCountry();

    return new SupplierResponseDto(
        supplier.getCompanyName(),
        supplier.getContactName(),
        supplier.getPhoneNumber(),
        supplier.getEmail(),
        address);
  }
}
