package com.javv.inventorySystem.presentation.transaction.inbound.dto;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

// @formatter:off
public record InboundRegisterDto(
    @NotBlank(message = "Inbound Register DTO: Supplier Code is required for register.") 
    String supplierCode,

    @NotBlank(message = "Inbound Register DTO: Encoder Username is required for register.") 
    String encoderUsername,

    @NotBlank(message = "Inbound Register DTO: Invoice Number is required for register.") 
    String invoiceNumber,

    @NotNull(message = "Inbound Register DTO: Date Received is required for register.") 
    LocalDateTime dateReceived,

    @NotEmpty(message = "Inbound Register DTO: List Items is required for registration.") 
    @Valid
    List<InboundItemRegisterDto> listInboundItem) {
}
