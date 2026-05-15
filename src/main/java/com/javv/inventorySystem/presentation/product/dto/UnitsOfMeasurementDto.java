package com.javv.inventorySystem.presentation.product.dto;

import jakarta.validation.constraints.NotBlank;

public record UnitsOfMeasurementDto(
    @NotBlank(message = "Units of Measurement Name is required.") String name,
    String abbreviation) {}
