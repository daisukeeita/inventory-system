package com.javv.inventorySystem.application.command.supplier;

public record SupplierRegisterCommand(
    String companyName,
    String contractName,
    String phoneNumber,
    String email,
    String street,
    String city,
    String state,
    String postalCode,
    String country

) {
}
