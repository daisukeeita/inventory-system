package com.javv.inventorySystem.presentation.unitsOfMeasure.dto;

import jakarta.validation.constraints.NotBlank;

public record UnitsOfMeasureUpdateDto(
    @NotBlank(message = "Measure Update DTO: Name is required to update.") String name,
    @NotBlank(message = "Measure Update DTO: Abbreviation is required to update")
        String abbreviation) {}
