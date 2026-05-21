package com.javv.inventorySystem.presentation.product;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.javv.inventorySystem.application.command.product.ProductRegisterCommand;
import com.javv.inventorySystem.application.command.product.ProductUpdateCommand;
import com.javv.inventorySystem.domain.model.product.Product;
import com.javv.inventorySystem.presentation.product.dto.ProductRegisterDto;
import com.javv.inventorySystem.presentation.product.dto.ProductResponseDto;
import com.javv.inventorySystem.presentation.product.dto.ProductUpdateDto;

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

  public ProductUpdateCommand toUpdateCommand(ProductUpdateDto productUpdateDto) {

    Objects.requireNonNull(productUpdateDto,
        "Product DTO Mapper: Cannot map a null ProductUpdateDto to a Command Record.");

    return new ProductUpdateCommand(
        productUpdateDto.sku(),
        productUpdateDto.name(),
        productUpdateDto.supplier(),
        productUpdateDto.baseUnitOfMeasure());
  }

  public ProductResponseDto toResponseDto(Product product) {

    Objects.requireNonNull(product,
        "Product DTO Mapper: Cannot map a null Product to a Response DTO Record.");

    return new ProductResponseDto(
        product.getId(),
        product.getSku().trim(),
        product.getName().trim(),
        product.getSupplier().getCompanyName().trim(),
        product.getBaseUnitsOfMeasure().getName().trim());
  }

}
