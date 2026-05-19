package com.javv.inventorySystem.presentation.unitsOfMeasure.dto;

import jakarta.validation.constraints.NotBlank;

public record UnitsOfMeasureRegisterDto(
    @NotBlank(message = "Measure Register DTO: Name is required.") String name,
    String abbreviation) {}
