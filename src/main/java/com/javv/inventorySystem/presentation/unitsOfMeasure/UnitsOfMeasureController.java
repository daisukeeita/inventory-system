package com.javv.inventorySystem.presentation.unitsOfMeasure;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.javv.inventorySystem.application.command.product.UnitsOfMeasureCommand;
import com.javv.inventorySystem.application.service.product.UnitsOfMeasureService;
import com.javv.inventorySystem.domain.model.product.UnitsOfMeasure;
import com.javv.inventorySystem.presentation.shared.payload.ApiResponse;
import com.javv.inventorySystem.presentation.unitsOfMeasure.dto.UnitsOfMeasureDto;
import com.javv.inventorySystem.presentation.unitsOfMeasure.dto.UnitsOfMeasureResponseDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/uom")
public class UnitsOfMeasureController {
  private UnitsOfMeasureService unitsOfMeasureService;
  private UnitsOfMeasureDtoMapper unitsOfMeasureDtoMapper;

  public UnitsOfMeasureController(
      UnitsOfMeasureService unitsOfMeasureService,
      UnitsOfMeasureDtoMapper unitsOfMeasureDtoMapper) {
    this.unitsOfMeasureService = unitsOfMeasureService;
    this.unitsOfMeasureDtoMapper = unitsOfMeasureDtoMapper;
  }

  @PostMapping("/save")
  @ResponseStatus(HttpStatus.CREATED)
  public ApiResponse<UnitsOfMeasureResponseDto> saveUom(
      @Valid @RequestBody UnitsOfMeasureDto unitsOfMeasurementDto) {

    UnitsOfMeasureCommand unitsOfMeasureCommand =
        unitsOfMeasureDtoMapper.toCommandRecord(unitsOfMeasurementDto);

    UnitsOfMeasure unitsOfMeasure = unitsOfMeasureService.saveMeasure(unitsOfMeasureCommand);

    UnitsOfMeasureResponseDto responseDto = unitsOfMeasureDtoMapper.toResponseDto(unitsOfMeasure);

    return ApiResponse.success(
        responseDto, "Units of Measure created successfully.", HttpStatus.CREATED.value());
  }

  @PutMapping("/update/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ApiResponse<UnitsOfMeasureResponseDto> updateUom(
      @PathVariable Integer id, @RequestBody UnitsOfMeasureDto unitsOfMeasureDto) {

    UnitsOfMeasureCommand unitsOfMeasureCommand =
        unitsOfMeasureDtoMapper.toCommandRecord(unitsOfMeasureDto);

    UnitsOfMeasure unitsOfMeasure = unitsOfMeasureService.updateMeasure(id, unitsOfMeasureCommand);

    UnitsOfMeasureResponseDto responseDto = unitsOfMeasureDtoMapper.toResponseDto(unitsOfMeasure);

    return ApiResponse.success(
        responseDto, "Units of Measure updated successfully.", HttpStatus.OK.value());
  }
}
