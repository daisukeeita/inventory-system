package com.javv.inventorySystem.presentation.transaction.inbound.dto;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// @formatter:off
public record InboundRegisterDto(
    @NotBlank(message = "Inbound Register DTO: Supplier Name is required for register.") 
    String supplierName,

    @NotBlank(message = "Inbound Register DTO: Encoder Username is required for register.") 
    String username,

    @NotBlank(message = "Inbound Register DTO: Invoice Number is required for register.") 
    String invoiceNumber,

    @NotNull(message = "Inbound Register DTO: Date Received is required for register.") 
    LocalDateTime dateReceived,

    @NotNull(message = "Inbound Register DTO: List Items is required for registration.") 
    List<InboundItemRegisterDto> listInboundItem) {
}
