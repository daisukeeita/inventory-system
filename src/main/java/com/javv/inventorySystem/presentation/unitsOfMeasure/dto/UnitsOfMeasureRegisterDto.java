package com.javv.inventorySystem.presentation.unitsOfMeasure.dto;

import jakarta.validation.constraints.NotBlank;

// @formatter:off
public record UnitsOfMeasureRegisterDto(
    @NotBlank(message = "Measure Register DTO: Name is required.") 
    String name,

    @NotBlank(message = "Measure Register DTO: Abbreviation is required.")
    String abbreviation) {}
