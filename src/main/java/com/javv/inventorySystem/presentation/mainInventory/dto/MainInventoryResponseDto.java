package com.javv.inventorySystem.presentation.mainInventory.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MainInventoryResponseDto(
    @NotNull(message = "Main Inventory Response DTO: ID cannot be null.") Integer id,
    @NotBlank(message = "Main Inventory Response DTO: Product SKU cannot be null.") String sku,
    @NotBlank(message = "Main Inventory Response DTO: Product Name cannot be null.") String productName,
    @NotNull(message = "Main Inventory Response DTO: Quantity on Hand cannot be null.") int quantityOnHand,
    @NotNull(message = "Main Inventory Response DTO: Reorder Level cannot be null.") int reorderLevel) {
}
