package com.javv.inventorySystem.application.command.supplier;

public record SupplierUpdateAddressCommand(
    String street, String city, String state, String postalCode, String country) {}
