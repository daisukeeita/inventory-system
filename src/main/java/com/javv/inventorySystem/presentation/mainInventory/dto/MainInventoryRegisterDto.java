package com.javv.inventorySystem.presentation.mainInventory.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// @formatter:off
public record MainInventoryRegisterDto(
    @NotBlank(message = "Main Inventory Register DTO: Product SKU is required for registration.") 
    String productSku,

    @NotNull(message = "Main Inventory Register DTO: Quantity is required for registration.") 
    @Min(value = 0, message = "Main Inventory Register DTO: Quantity should not be less than 0.") 
    Integer quantityOnHand,

    @NotNull(message = "Main Inventory Register DTO: Reorder Level is required for registration.") 
    @Min(value = 0, message = "Main Inventory Register DTO: Reorder Level should not be less than 0.") 
    Integer reorderLevel) {
}
