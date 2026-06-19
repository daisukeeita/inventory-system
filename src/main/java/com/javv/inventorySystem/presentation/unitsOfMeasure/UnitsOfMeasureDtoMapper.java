package com.javv.inventorySystem.presentation.unitsOfMeasure;

import org.springframework.stereotype.Component;

import com.javv.inventorySystem.application.command.unitsOfMeasure.UnitsOfMeasureRegisterCommand;
import com.javv.inventorySystem.application.command.unitsOfMeasure.UnitsOfMeasureUpdateCommand;
import com.javv.inventorySystem.domain.exception.ObjectMappingException;
import com.javv.inventorySystem.domain.model.product.UnitsOfMeasure;
import com.javv.inventorySystem.presentation.unitsOfMeasure.dto.UnitsOfMeasureRegisterDto;
import com.javv.inventorySystem.presentation.unitsOfMeasure.dto.UnitsOfMeasureResponseDto;
import com.javv.inventorySystem.presentation.unitsOfMeasure.dto.UnitsOfMeasureUpdateDto;

@Component
public class UnitsOfMeasureDtoMapper {

  public UnitsOfMeasureRegisterCommand toRegisterCommandRecord(
      UnitsOfMeasureRegisterDto unitsOfMeasureRegisterDto) {

    if (unitsOfMeasureRegisterDto == null) {
      throw new ObjectMappingException(
          "Units of Measure DTO Mapper: Cannot map a null UnitsOfRegisterDto to a Command Record.");
    }

    return new UnitsOfMeasureRegisterCommand(
        unitsOfMeasureRegisterDto.name(),
        unitsOfMeasureRegisterDto.abbreviation());
  }

  public UnitsOfMeasureUpdateCommand toUpdateCommandRecord(
      UnitsOfMeasureUpdateDto unitsOfMeasureUpdateDto) {

    if (unitsOfMeasureUpdateDto == null) {
      throw new ObjectMappingException(
          "Units of Measure DTO Mapper: Cannot map a null UnitsOfMeasureUpdateDto to a Command Record.");
    }

    return new UnitsOfMeasureUpdateCommand(
        unitsOfMeasureUpdateDto.name(),
        unitsOfMeasureUpdateDto.abbreviation());
  }

  public UnitsOfMeasureResponseDto toResponseDto(UnitsOfMeasure unitsOfMeasure) {

    if (unitsOfMeasure == null) {
      throw new ObjectMappingException(
          "Unts of Measure DTO Mapper: Cannot map a null UnitsOfMeasure to a Response DTO.");
    }

    return new UnitsOfMeasureResponseDto(
        unitsOfMeasure.getName().trim(),
        unitsOfMeasure.getAbbreviation().trim(),
        unitsOfMeasure.getCreatedAt(),
        unitsOfMeasure.getUpdatedAt());
  }
}
