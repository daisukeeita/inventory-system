package com.javv.inventorySystem.presentation.product.dto;

import java.util.List;

import com.javv.inventorySystem.presentation.productPackaging.dto.ProductPackagingResponseDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// @formatter:off
public record ProductResponseDto(
    @NotNull(message = "Product Response DTO: ID is required for response.") 
    Long id,

    @NotBlank(message = "Product Response DTO: SKU is required for response.") 
    String sku,

    @NotBlank(message = "Product Response DTO: Product Name is required for response.") 
    String name,

    @NotNull(message = "Product Response DTO: Supplier ID is required for response.")
    int supplierId,

    @NotBlank(message = "Product Response DTO: Supplier Name is required for response.") 
    String supplierName,

    @NotNull(message = "Product Response DTO: Base Unit of Measure ID is required for response.")
    int baseUnitOfMeasureId,

    @NotBlank(message = "Product Response DTO: Base Unit of Measure is required for response.") 
    String baseUnitOfMeasure,

    @NotNull(message = "Product Response DTO: List of Packaging is required for response.")
    List<ProductPackagingResponseDto> listPackaging
    ) {
}
