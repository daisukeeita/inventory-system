package com.javv.inventorySystem.presentation.product;

import org.springframework.stereotype.Component;

import com.javv.inventorySystem.application.command.product.ProductRegisterCommand;
import com.javv.inventorySystem.domain.model.product.Product;
import com.javv.inventorySystem.presentation.product.dto.ProductRegistrationDto;
import com.javv.inventorySystem.presentation.product.dto.ProductResponseDto;

@Component
public class ProductDtoMapper {

  public ProductRegisterCommand toRegisterCommand(ProductRegistrationDto productRegistrationDto) {

    return new ProductRegisterCommand(
        productRegistrationDto.sku(),
        productRegistrationDto.name(),
        productRegistrationDto.supplier(),
        productRegistrationDto.baseUnitOfMeasure());
  }

  public ProductResponseDto toResponseDto(Product product) {

    return new ProductResponseDto(
        product.getSku(),
        product.getName(),
        product.getSupplier().getCompanyName(),
        product.getBaseUnitsOfMeasure().getName());
  }
}
