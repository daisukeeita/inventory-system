package com.javv.inventorySystem.application.command.product;

public record ProductRegisterCommand(
    String sku,
    String name,
    String supplier,
    String baseUnitOfMeasure) {
}
