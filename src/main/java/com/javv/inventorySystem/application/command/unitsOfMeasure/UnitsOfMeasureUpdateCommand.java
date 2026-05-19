package com.javv.inventorySystem.application.command.unitsOfMeasure;

import java.util.Objects;

public record UnitsOfMeasureUpdateCommand(String name, String abbreviation) {

  public UnitsOfMeasureUpdateCommand {
    Objects.requireNonNull(name, "Measure Update Command Record: Name cannot be null");

    Objects.requireNonNull(
        abbreviation, "Measure Update Command Record: Abbreviation cannot be null");
  }
}
