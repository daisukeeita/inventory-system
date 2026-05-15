package com.javv.inventorySystem.presentation.product;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.javv.inventorySystem.application.command.product.UnitsOfMeasureCommand;
import com.javv.inventorySystem.application.service.product.UnitsOfMeasureService;
import com.javv.inventorySystem.domain.model.product.UnitsOfMeasure;
import com.javv.inventorySystem.presentation.product.dto.UnitsOfMeasurementDto;
import com.javv.inventorySystem.presentation.product.dto.UnitsOfMeasurementResponseDto;
import com.javv.inventorySystem.presentation.shared.payload.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/uom")
public class UnitsOfMeasureController {
  private UnitsOfMeasureService unitsOfMeasureService;
  private UnitsOfMeasurementDtoMapper unitsOfMeasurementDtoMapper;

  public UnitsOfMeasureController(
      UnitsOfMeasureService unitsOfMeasureService,
      UnitsOfMeasurementDtoMapper unitsOfMeasurementDtoMapper) {
    this.unitsOfMeasureService = unitsOfMeasureService;
    this.unitsOfMeasurementDtoMapper = unitsOfMeasurementDtoMapper;
  }

  @PostMapping("/save")
  @ResponseStatus(HttpStatus.CREATED)
  public ApiResponse<UnitsOfMeasurementResponseDto> saveUom(
      @Valid @RequestBody UnitsOfMeasurementDto unitsOfMeasurementDto) {
    UnitsOfMeasureCommand unitsOfMeasureCommand =
        unitsOfMeasurementDtoMapper.toCommandRecord(unitsOfMeasurementDto);

    UnitsOfMeasure unitsOfMeasure = unitsOfMeasureService.saveMeasure(unitsOfMeasureCommand);

    UnitsOfMeasurementResponseDto responseDto =
        unitsOfMeasurementDtoMapper.toResponseDto(unitsOfMeasure);

    return ApiResponse.success(
        responseDto, "Units of Measure created successfully.", HttpStatus.CREATED.value());
  }
}
