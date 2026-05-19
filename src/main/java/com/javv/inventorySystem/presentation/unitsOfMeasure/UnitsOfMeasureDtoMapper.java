package com.javv.inventorySystem.presentation.unitsOfMeasure;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.javv.inventorySystem.application.command.unitsOfMeasure.UnitsOfMeasureRegisterCommand;
import com.javv.inventorySystem.application.command.unitsOfMeasure.UnitsOfMeasureUpdateCommand;
import com.javv.inventorySystem.domain.model.product.UnitsOfMeasure;
import com.javv.inventorySystem.presentation.unitsOfMeasure.dto.UnitsOfMeasureRegisterDto;
import com.javv.inventorySystem.presentation.unitsOfMeasure.dto.UnitsOfMeasureResponseDto;
import com.javv.inventorySystem.presentation.unitsOfMeasure.dto.UnitsOfMeasureUpdateDto;

@Component
public class UnitsOfMeasureDtoMapper {

  public UnitsOfMeasureRegisterCommand toRegisterCommandRecord(
      UnitsOfMeasureRegisterDto unitsOfMeasureRegisterDto) {

    Objects.requireNonNull(
        unitsOfMeasureRegisterDto,
        "Measure DTO Mapper: Cannot map a null UnitsOfMeasureRegisterDto to a Command Record.");

    return new UnitsOfMeasureRegisterCommand(
        unitsOfMeasureRegisterDto.name(), unitsOfMeasureRegisterDto.abbreviation());
  }

  public UnitsOfMeasureUpdateCommand toUpdateCommandRecord(
      UnitsOfMeasureUpdateDto unitsOfMeasureUpdateDto) {

    Objects.requireNonNull(
        unitsOfMeasureUpdateDto,
        "Measure DTO Mapper: Cannot map a null UnitsOfMeasureUpdateDto to a Command Record.");

    return new UnitsOfMeasureUpdateCommand(
        unitsOfMeasureUpdateDto.name(), unitsOfMeasureUpdateDto.abbreviation());
  }

  public UnitsOfMeasureResponseDto toResponseDto(UnitsOfMeasure unitsOfMeasure) {

    Objects.requireNonNull(
        unitsOfMeasure,
        "Measure DTO Mapper: Cannot map a null UnitsOfMeasure to a Response Record.");

    return new UnitsOfMeasureResponseDto(
        unitsOfMeasure.getId(),
        unitsOfMeasure.getName().trim(),
        unitsOfMeasure.getAbbreviation().trim(),
        unitsOfMeasure.getCreatedAt(),
        unitsOfMeasure.getUpdatedAt());
  }
}
