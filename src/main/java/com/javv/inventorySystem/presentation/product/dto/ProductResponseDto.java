package com.javv.inventorySystem.presentation.product.dto;

import java.util.List;

import com.javv.inventorySystem.presentation.productPackaging.dto.ProductPackagingResponseDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// @formatter:off
public record ProductResponseDto(

    @NotBlank(message = "Product Response DTO: SKU is required for response.") 
    String sku,

    @NotBlank(message = "Product Response DTO: Product Name is required for response.") 
    String name,

    @NotBlank(message = "Product Response DTO: Supplier Code is required for response.")
    String supplierCode,

    @NotBlank(message = "Product Response DTO: Supplier Name is required for response.") 
    String supplierName,

    @NotBlank(message = "Product Response DTO: Base Unit of Measure is required for response.") 
    String baseUnitOfMeasure,

    @NotNull(message = "Product Response DTO: List of Packaging is required for response.")
    @Valid
    List<ProductPackagingResponseDto> listPackaging
    ) {
}
