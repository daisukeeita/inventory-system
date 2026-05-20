package com.javv.inventorySystem.presentation.product;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.javv.inventorySystem.application.command.product.ProductRegisterCommand;
import com.javv.inventorySystem.domain.model.product.Product;
import com.javv.inventorySystem.presentation.product.dto.ProductRegisterDto;
import com.javv.inventorySystem.presentation.product.dto.ProductResponseDto;

@Component
public class ProductDtoMapper {

  public ProductRegisterCommand toRegisterCommand(ProductRegisterDto productRegisterDto) {

    Objects.requireNonNull(productRegisterDto,
        "Product DTO Mapper: Cannot map a null ProductRegisterDto to a Command Record.");

    return new ProductRegisterCommand(
        productRegisterDto.sku().trim(),
        productRegisterDto.name().trim(),
        productRegisterDto.supplier().trim(),
        productRegisterDto.baseUnitOfMeasure().trim());
  }

  public ProductResponseDto toResponseDto(Product product) {

    return new ProductResponseDto(
        product.getSku().trim(),
        product.getName().trim(),
        product.getSupplier().getCompanyName().trim(),
        product.getBaseUnitsOfMeasure().getName().trim());
  }
}
