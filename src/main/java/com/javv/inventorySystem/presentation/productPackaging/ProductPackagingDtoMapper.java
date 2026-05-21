package com.javv.inventorySystem.presentation.productPackaging;

import java.util.Objects;

import com.javv.inventorySystem.application.command.productPackaging.ProductPackagingRegisterCommand;
import com.javv.inventorySystem.application.command.productPackaging.ProductPackagingUpdateCommand;
import com.javv.inventorySystem.domain.model.product.ProductPackaging;
import com.javv.inventorySystem.presentation.productPackaging.dto.ProductPackagingRegisterDto;
import com.javv.inventorySystem.presentation.productPackaging.dto.ProductPackagingResponseDto;
import com.javv.inventorySystem.presentation.productPackaging.dto.ProductPackagingUpdateDto;

public class ProductPackagingDtoMapper {

  public ProductPackagingRegisterCommand toRegisterCommand(
      ProductPackagingRegisterDto productPackagingRegisterDto) {

    Objects.requireNonNull(
        productPackagingRegisterDto,
        "Product Packaging DTO Mapper: Cannot map a null ProductPackagingRegisterDto to a Command"
            + " Record.");

    return new ProductPackagingRegisterCommand(
        productPackagingRegisterDto.packagingCode(),
        productPackagingRegisterDto.sku(),
        productPackagingRegisterDto.unitsOfMeasure(),
        productPackagingRegisterDto.conversionFactor(),
        productPackagingRegisterDto.price());
  }

  public ProductPackagingUpdateCommand toUpdateCommand(
      ProductPackagingUpdateDto productPackagingUpdateDto) {

    Objects.requireNonNull(
        productPackagingUpdateDto,
        "Product Packaging DTO Mapper: Cannot map a null ProductPackagingUpdateDto to a Command"
            + " Record.");

    return new ProductPackagingUpdateCommand(
        productPackagingUpdateDto.id(),
        productPackagingUpdateDto.packagingCode(),
        productPackagingUpdateDto.sku(),
        productPackagingUpdateDto.unitOfMeasure(),
        productPackagingUpdateDto.conversionFactor(),
        productPackagingUpdateDto.price());
  }

  public ProductPackagingResponseDto toResponseDto(ProductPackaging productPackaging) {

    Objects.requireNonNull(
        productPackaging,
        "Product Packaging DTO Mapper: Cannot map a null ProductPackaging to a Response DTO"
            + " Record.");

    return new ProductPackagingResponseDto(
        productPackaging.getId(),
        productPackaging.getPackagingCode(),
        productPackaging.getProduct().getSku(),
        productPackaging.getProduct().getName(),
        productPackaging.getUnitsOfMeasure().getName(),
        productPackaging.getConversionFactor(),
        productPackaging.getPrice());
  }
}
