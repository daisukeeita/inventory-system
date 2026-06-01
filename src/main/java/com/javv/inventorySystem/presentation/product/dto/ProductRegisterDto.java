package com.javv.inventorySystem.presentation.product.dto;

import java.util.List;

import com.javv.inventorySystem.presentation.productPackaging.dto.ProductPackagingRegisterDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// @formatter:off
public record ProductRegisterDto(
    @NotBlank(message = "Product Register DTO: SKU is required.") 
    String sku,

    @NotBlank(message = "Product Register DTO: Product Name is required.") 
    String name,

    @NotNull(message = "Product Register DTO: Supplier Name is required.") 
    int supplierId,

    @NotNull(message = "Product Register DTO: Base Unit of Measure is required.") 
    int baseUnitOfMeasureId,

    @NotNull(message = "Product Register DTO: Product Packaging is required.")
    List<ProductPackagingRegisterDto> listPackagingDto
    ) {
}
