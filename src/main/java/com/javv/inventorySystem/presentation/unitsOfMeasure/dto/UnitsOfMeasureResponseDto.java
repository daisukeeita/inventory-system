package com.javv.inventorySystem.presentation.unitsOfMeasure.dto;

import java.time.Instant;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// @formatter:off
public record UnitsOfMeasureResponseDto(
    @NotNull(message = "Measure Response DTO: Measure ID is required for response.") 
    Integer id,

    @NotBlank(message = "Measure Response DTO: Measure Name is required for response.") 
    String name,

    @NotBlank(message = "Measure Response DTO: Measure Abbreviation is required for response.")
    String abbreviation,

    @NotNull(message = "Measure Response DTO: Measure Created At is required for response.")
    Instant createdAt,

    @NotNull(message = "Measure Response DTO: Measure Updated At is required for response.")
    Instant updatedAt) {}
