package com.javv.inventorySystem.application.command.unitsOfMeasure;

import java.util.Objects;

public record UnitsOfMeasureRegisterCommand(String name, String abbreviation) {

  public UnitsOfMeasureRegisterCommand {
    Objects.requireNonNull(name, "Measure Register Command Record: Name cannot be null.");
  }
}
