package com.javv.inventorySystem.presentation.supplier.dto;

public record SupplierResponseDto(
    String companyName,
    String contactName,
    String phoneNumber,
    String email,
    String address) {
}
