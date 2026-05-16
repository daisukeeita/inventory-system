package com.javv.inventorySystem.presentation.unitsOfMeasure.dto;

import jakarta.validation.constraints.NotBlank;

public record UnitsOfMeasureDto(
    @NotBlank(message = "Units of Measurement Name is required.") String name,
    String abbreviation) {}
