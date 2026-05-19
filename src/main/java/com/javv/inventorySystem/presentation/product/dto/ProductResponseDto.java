package com.javv.inventorySystem.presentation.product.dto;

public record ProductResponseDto(
    String sku, String name, String supplier, String baseUnitOfMeasure) {}
