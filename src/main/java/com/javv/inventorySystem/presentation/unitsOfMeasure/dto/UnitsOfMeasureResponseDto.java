package com.javv.inventorySystem.presentation.unitsOfMeasure.dto;

import java.time.Instant;

public record UnitsOfMeasureResponseDto(
    Integer id, String name, String abbreviation, Instant createdAt, Instant updatedAt) {}
