package com.javv.inventorySystem.presentation.transaction.inbound.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

// @formatter:off
public record InboundRegisterDto(
    @NotNull(message = "Inbound Register DTO: Supplier ID is required for register.") 
    Integer supplierId,

    @NotNull(message = "Inbound Register DTO: Encoder Username is required for register.") 
    UUID encoderId,

    @NotBlank(message = "Inbound Register DTO: Invoice Number is required for register.") 
    String invoiceNumber,

    @NotNull(message = "Inbound Register DTO: Date Received is required for register.") 
    LocalDateTime dateReceived,

    @NotEmpty(message = "Inbound Register DTO: List Items is required for registration.") 
    @Valid
    List<InboundItemRegisterDto> listInboundItem) {
}
