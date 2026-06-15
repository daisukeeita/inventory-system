package com.javv.inventorySystem.presentation.product.dto;

import java.util.List;

import com.javv.inventorySystem.presentation.productPackaging.dto.ProductPackagingUpdateDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// @formatter:off
public record ProductUpdateDto(
    @NotBlank(message = "Product Update DTO: SKU is required for update.") 
    String sku,

    @NotBlank(message = "Product Update DTO: Name is required for update.") 
    String name,

    @NotNull(message = "Product Update DTO: Supplier ID is required for update.") 
    Integer supplierId,

    @NotNull(message = "Product Update DTO: Base Units of Measure ID is required for update.") 
    Integer baseUnitOfMeasureId,

    @NotNull(message = "Product Update DTO: List of Packages is required for update.")
    @Valid
    List<ProductPackagingUpdateDto> listPackages
    ) {
}
