package com.javv.inventorySystem.presentation.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;

import com.javv.inventorySystem.application.command.product.ProductRegisterCommand;
import com.javv.inventorySystem.application.command.product.ProductUpdateCommand;
import com.javv.inventorySystem.application.command.productPackaging.ProductPackagingRegisterCommand;
import com.javv.inventorySystem.domain.model.product.Product;
import com.javv.inventorySystem.presentation.product.dto.ProductRegisterDto;
import com.javv.inventorySystem.presentation.product.dto.ProductResponseDto;
import com.javv.inventorySystem.presentation.product.dto.ProductUpdateDto;

@Component
public class ProductDtoMapper {

  public ProductRegisterCommand toRegisterCommand(ProductRegisterDto productRegisterDto) {

    Objects.requireNonNull(productRegisterDto,
        "Product DTO Mapper: Cannot map a null ProductRegisterDto to a Command Record.");

    Objects.requireNonNull(productRegisterDto.listPackagingDto(),
        "Product DTO Mapper: Cannot map a null ProductPackagingRegisterDto to a Command Record.");

    List<ProductPackagingRegisterCommand> listPackaging = new ArrayList<ProductPackagingRegisterCommand>();

    productRegisterDto.listPackagingDto().forEach(
        packaging -> listPackaging.add(new ProductPackagingRegisterCommand(
            packaging.packagingCode(),
            packaging.productId(),
            packaging.unitsOfMeasureId(),
            packaging.conversionFactor(),
            packaging.price())));

    return new ProductRegisterCommand(
        productRegisterDto.sku().trim(),
        productRegisterDto.name().trim(),
        productRegisterDto.supplierId(),
        productRegisterDto.baseUnitOfMeasureId(),
        listPackaging);
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

  public ProductResponseDto toResponseDto(
      Product product,
      String supplierName,
      String baseUnitOfMeasureName) {

    Objects.requireNonNull(product,
        "Product DTO Mapper: Cannot map a null Product to a Response DTO Record.");

    return new ProductResponseDto(
        product.getId(),
        product.getSku(),
        product.getName(),
        product.getSupplierId(),
        supplierName,
        product.getBaseUnitsOfMeasureId(),
        baseUnitOfMeasureName);
  }

}
