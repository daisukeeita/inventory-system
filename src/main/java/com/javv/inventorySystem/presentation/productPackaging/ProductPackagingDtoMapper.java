package com.javv.inventorySystem.presentation.productPackaging;

import org.springframework.stereotype.Component;

@Component
public class ProductPackagingDtoMapper {

  // public ProductPackagingRegisterCommand toRegisterCommand(
  // ProductPackagingRegisterDto productPackagingRegisterDto) {
  //
  // Objects.requireNonNull(
  // productPackagingRegisterDto,
  // "Product Packaging DTO Mapper: Cannot map a null ProductPackagingRegisterDto
  // to a Command"
  // + " Record.");
  //
  // return new ProductPackagingRegisterCommand(
  // productPackagingRegisterDto.packagingCode(),
  // productPackagingRegisterDto.sku(),
  // productPackagingRegisterDto.unitsOfMeasure(),
  // productPackagingRegisterDto.conversionFactor(),
  // productPackagingRegisterDto.price());
  // }

  // public ProductPackagingUpdateCommand toUpdateCommand(
  // ProductPackagingUpdateDto productPackagingUpdateDto) {
  //
  // Objects.requireNonNull(
  // productPackagingUpdateDto,
  // "Product Packaging DTO Mapper: Cannot map a null ProductPackagingUpdateDto to
  // a Command"
  // + " Record.");
  //
  // return new ProductPackagingUpdateCommand(
  // productPackagingUpdateDto.id(),
  // productPackagingUpdateDto.packagingCode(),
  // productPackagingUpdateDto.sku(),
  // productPackagingUpdateDto.unitOfMeasure(),
  // productPackagingUpdateDto.conversionFactor(),
  // productPackagingUpdateDto.price());
  // }

  // public ProductPackagingResponseDto toResponseDto(ProductPackaging
  // productPackaging) {
  //
  // Objects.requireNonNull(
  // productPackaging,
  // "Product Packaging DTO Mapper: Cannot map a null ProductPackaging to a
  // Response DTO"
  // + " Record.");
  //
  // return new ProductPackagingResponseDto(
  // productPackaging.getId(),
  // productPackaging.getPackagingCode(),
  // productPackaging.getProductId().getSku(),
  // productPackaging.getProductId().getName(),
  // productPackaging.getUnitsOfMeasureId().getName(),
  // productPackaging.getConversionFactor(),
  // productPackaging.getPrice());
  // }
}
