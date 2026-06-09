package com.javv.inventorySystem.presentation.transaction.inbound.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

// @formatter:off
public record InboundItemRegisterDto(

    @NotNull(message = "Inbound Item Register DTO: Product ID is required for registration.")
    @Positive(message = "Inbound Item Register DTO: Product ID must be greater than zero.")
    Long productId,

    @NotNull(message = "Inbound Item Register DTO: Packaging Code is required for registration.") 
    @Positive(message = "Inbound Item Register DTO: Packaging ID must be greater than zero.")
    Long packagingId,

    @NotNull(message = "Inbound Item Register DTO: Quantity Received is required for registration.") 
    @Min(value = 0, message = "Inbound Item Register DTO: Quantity Received should not be less than zero.") 
    Integer quantityReceived

) {}
