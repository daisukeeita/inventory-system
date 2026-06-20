package com.javv.inventorySystem.presentation.product.dto;

import java.util.List;

import com.javv.inventorySystem.presentation.productPackaging.dto.ProductPackagingRegisterDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// @formatter:off
public record ProductRegisterDto(
    @NotBlank(message = "Product Register DTO: SKU is required.") 
    String sku,

    @NotBlank(message = "Product Register DTO: Product Name is required.") 
    String name,

    @NotBlank(message = "Product Register DTO: Supplier Code is required.") 
    String supplierCode,

    @NotBlank(message = "Product Register DTO: Base Unit of Measure Name is required.") 
    String baseUnitOfMeasure,

    @NotNull(message = "Product Register DTO: Product Packaging is required.")
    @Valid
    List<ProductPackagingRegisterDto> listPackagingRegisterDto
    ) {
}
