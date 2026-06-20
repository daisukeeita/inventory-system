package com.javv.inventorySystem.presentation.transaction.inbound.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// @formatter:off
public record InboundItemRegisterDto(

    @NotBlank(message = "Inbound Item Register DTO: Product SKU is required for registration.")
    String productSku,

    @NotBlank(message = "Inbound Item Register DTO: Packaging Code is required for registration.") 
    String packagingCode,

    @NotNull(message = "Inbound Item Register DTO: Quantity Received is required for registration.") 
    @Min(value = 0, message = "Inbound Item Register DTO: Quantity Received should not be less than zero.") 
    Integer quantityReceived

) {}
