package com.javv.inventorySystem.presentation.product;

import org.springframework.stereotype.Component;

import com.javv.inventorySystem.application.command.product.UnitsOfMeasureCommand;
import com.javv.inventorySystem.domain.model.product.UnitsOfMeasure;
import com.javv.inventorySystem.presentation.product.dto.UnitsOfMeasurementDto;
import com.javv.inventorySystem.presentation.product.dto.UnitsOfMeasurementResponseDto;

@Component
public class UnitsOfMeasurementDtoMapper {

  public UnitsOfMeasureCommand toCommandRecord(UnitsOfMeasurementDto unitsOfMeasurementDto) {
    return new UnitsOfMeasureCommand(
        unitsOfMeasurementDto.name(), unitsOfMeasurementDto.abbreviation());
  }

  public UnitsOfMeasurementResponseDto toResponseDto(UnitsOfMeasure unitsOfMeasure) {
    return new UnitsOfMeasurementResponseDto(
        unitsOfMeasure.getId(), unitsOfMeasure.getName(), unitsOfMeasure.getAbbreviation());
  }
}
