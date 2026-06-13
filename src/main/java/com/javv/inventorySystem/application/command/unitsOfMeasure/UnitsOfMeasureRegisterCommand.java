package com.javv.inventorySystem.application.command.unitsOfMeasure;

import com.javv.inventorySystem.domain.exception.RecordInitializationException;

public record UnitsOfMeasureRegisterCommand(
    String name,
    String abbreviation) {

  public UnitsOfMeasureRegisterCommand {

    if (name == null || name.isBlank()) {
      throw new RecordInitializationException(
          "Units of Measure Register Command: Name cannot be null or empty.");
    }

    if (abbreviation == null || abbreviation.isBlank()) {
      throw new RecordInitializationException(
          "Units of Measure Register Command: Abbreviation cannot be null or empty.");
    }
  }
}
