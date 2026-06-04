package com.javv.inventorySystem.presentation.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;

import com.javv.inventorySystem.application.command.product.ProductRegisterCommand;
import com.javv.inventorySystem.application.command.product.ProductResponseRead;
import com.javv.inventorySystem.application.command.product.ProductUpdateCommand;
import com.javv.inventorySystem.application.command.productPackaging.ProductPackagingRegisterCommand;
import com.javv.inventorySystem.presentation.product.dto.ProductRegisterDto;
import com.javv.inventorySystem.presentation.product.dto.ProductResponseDto;
import com.javv.inventorySystem.presentation.product.dto.ProductUpdateDto;
import com.javv.inventorySystem.presentation.productPackaging.dto.ProductPackagingResponseDto;

@Component
public class ProductDtoMapper {

  public ProductRegisterCommand toRegisterCommand(ProductRegisterDto productRegisterDto) {

    Objects.requireNonNull(productRegisterDto,
        "Product DTO Mapper: Cannot map a null ProductRegisterDto to a Command Record.");

    Objects.requireNonNull(productRegisterDto.listPackagingDto(),
        "Product DTO Mapper: Cannot map a null ProductPackagingRegisterDto to a Command Record.");

    List<ProductPackagingRegisterCommand> listPackagingCommand = new ArrayList<ProductPackagingRegisterCommand>();

    productRegisterDto.listPackagingDto().forEach(
        packaging -> listPackagingCommand.add(new ProductPackagingRegisterCommand(
            packaging.packagingCode().trim(),
            packaging.unitsOfMeasureId(),
            packaging.conversionFactor(),
            packaging.price())));

    return new ProductRegisterCommand(
        productRegisterDto.sku().trim(),
        productRegisterDto.name().trim(),
        productRegisterDto.supplierId(),
        productRegisterDto.baseUnitOfMeasureId(),
        listPackagingCommand);
  }

  public ProductUpdateCommand toUpdateCommand(ProductUpdateDto productUpdateDto) {

    Objects.requireNonNull(productUpdateDto,
        "Product DTO Mapper: Cannot map a null ProductUpdateDto to a Command Record.");

    return new ProductUpdateCommand(
        productUpdateDto.sku(),
        productUpdateDto.name(),
        productUpdateDto.supplierId(),
        productUpdateDto.baseUnitOfMeasureId());
  }

  public ProductResponseDto toResponseDto(ProductResponseRead responseRead) {

    Objects.requireNonNull(responseRead,
        "Product DTO Mapper: Cannot map a null ProductResponseRead to a Response DTO Record.");

    List<ProductPackagingResponseDto> listProductPackagingResponseDto = new ArrayList<>();

    responseRead.listProductPackaging().forEach(
        packaging -> listProductPackagingResponseDto.add(
            new ProductPackagingResponseDto(
                packaging.id(),
                packaging.packagingCode(),
                packaging.sku(),
                packaging.sku(),
                packaging.conversionFactor(),
                packaging.price())));

    return new ProductResponseDto(
        responseRead.id(),
        responseRead.sku(),
        responseRead.name(),
        responseRead.supplierId(),
        responseRead.supplierName(),
        responseRead.baseUnitOfMeasureId(),
        responseRead.baseUnitsOfMeasure(),
        listProductPackagingResponseDto);
  }

}
