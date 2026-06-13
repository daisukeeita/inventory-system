package com.javv.inventorySystem.application.command.unitsOfMeasure;

import com.javv.inventorySystem.domain.exception.RecordInitializationException;

public record UnitsOfMeasureUpdateCommand(
    String name,
    String abbreviation) {

  public UnitsOfMeasureUpdateCommand {

    if (name == null || name.isBlank()) {
      throw new RecordInitializationException(
          "Units of Measure Update Command: Name cannot be null or empty.");
    }

    if (abbreviation == null || abbreviation.isBlank()) {
      throw new RecordInitializationException(
          "Units of Measure Update Command: Abbreviation cannot be null or empty.");
    }

  }
}
