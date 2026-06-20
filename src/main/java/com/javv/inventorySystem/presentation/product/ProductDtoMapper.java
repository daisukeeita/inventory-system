package com.javv.inventorySystem.presentation.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.javv.inventorySystem.application.command.product.ProductRegisterCommand;
import com.javv.inventorySystem.application.command.product.ProductResponseRead;
import com.javv.inventorySystem.application.command.product.ProductUpdateCommand;
import com.javv.inventorySystem.application.command.productPackaging.ProductPackagingRegisterCommand;
import com.javv.inventorySystem.domain.exception.RecordInitializationException;
import com.javv.inventorySystem.presentation.product.dto.ProductRegisterDto;
import com.javv.inventorySystem.presentation.product.dto.ProductResponseDto;
import com.javv.inventorySystem.presentation.product.dto.ProductUpdateDto;
import com.javv.inventorySystem.presentation.productPackaging.dto.ProductPackagingResponseDto;

@Component
public class ProductDtoMapper {

  public ProductRegisterCommand toRegisterCommand(ProductRegisterDto productRegisterDto) {

    if (productRegisterDto == null) {
      throw new RecordInitializationException(
          "Product DTO Mapper: Cannot map a null ProductRegisterDTO to a Command.");
    }

    if (productRegisterDto.listPackagingRegisterDto() == null) {
      throw new RecordInitializationException(
          "Product DTO Mapper: Cannot map a null ProductPackagingRegisterDTO to a Command.");
    }

    List<ProductPackagingRegisterCommand> listPackagingCommand = new ArrayList<ProductPackagingRegisterCommand>();

    productRegisterDto.listPackagingRegisterDto().forEach(
        packaging -> listPackagingCommand.add(new ProductPackagingRegisterCommand(
            packaging.unitOfMeasure(),
            packaging.conversionFactor(),
            packaging.price())));

    return new ProductRegisterCommand(
        productRegisterDto.sku().trim(),
        productRegisterDto.name().trim(),
        productRegisterDto.supplierCode().trim(),
        productRegisterDto.baseUnitOfMeasure().trim(),
        listPackagingCommand);
  }

  public ProductUpdateCommand toUpdateCommand(ProductUpdateDto productUpdateDto) {

    if (productUpdateDto == null) {
      throw new RecordInitializationException(
          "Product DTO Mapper: Cannot map a null ProductUpdateDto to a Command.");
    }

    return new ProductUpdateCommand(
        productUpdateDto.sku(),
        productUpdateDto.name(),
        productUpdateDto.supplierId(),
        productUpdateDto.baseUnitOfMeasureId());
  }

  public ProductResponseDto toResponseDto(ProductResponseRead responseRead) {

    if (responseRead == null)
      throw new RecordInitializationException(
          "Product DTO Mapper: Cannot map a null ProductResponseRead to a Response DTO Record.");

    List<ProductPackagingResponseDto> listProductPackagingResponseDto = new ArrayList<>();

    responseRead.listProductPackaging().forEach(
        packaging -> listProductPackagingResponseDto.add(
            new ProductPackagingResponseDto(
                packaging.packagingCode(),
                packaging.sku(),
                packaging.productName(),
                packaging.unitOfMeasure(),
                packaging.conversionFactor(),
                packaging.price())));

    return new ProductResponseDto(
        responseRead.sku(),
        responseRead.name(),
        responseRead.supplierCode(),
        responseRead.supplierName(),
        responseRead.baseUnitsOfMeasure(),
        listProductPackagingResponseDto);
  }

}
