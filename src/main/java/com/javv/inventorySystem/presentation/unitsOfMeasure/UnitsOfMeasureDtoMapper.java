package com.javv.inventorySystem.presentation.unitsOfMeasure;

import org.springframework.stereotype.Component;

import com.javv.inventorySystem.application.command.product.UnitsOfMeasureCommand;
import com.javv.inventorySystem.domain.model.product.UnitsOfMeasure;
import com.javv.inventorySystem.presentation.unitsOfMeasure.dto.UnitsOfMeasureDto;
import com.javv.inventorySystem.presentation.unitsOfMeasure.dto.UnitsOfMeasureResponseDto;

@Component
public class UnitsOfMeasureDtoMapper {

  public UnitsOfMeasureCommand toCommandRecord(UnitsOfMeasureDto unitsOfMeasureDto) {
    return new UnitsOfMeasureCommand(unitsOfMeasureDto.name(), unitsOfMeasureDto.abbreviation());
  }

  public UnitsOfMeasureResponseDto toResponseDto(UnitsOfMeasure unitsOfMeasure) {
    return new UnitsOfMeasureResponseDto(
        unitsOfMeasure.getId(), unitsOfMeasure.getName(), unitsOfMeasure.getAbbreviation());
  }
}
