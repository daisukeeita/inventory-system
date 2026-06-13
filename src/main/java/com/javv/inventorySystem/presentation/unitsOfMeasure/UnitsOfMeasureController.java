package com.javv.inventorySystem.presentation.unitsOfMeasure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.javv.inventorySystem.application.command.unitsOfMeasure.UnitsOfMeasureRegisterCommand;
import com.javv.inventorySystem.application.command.unitsOfMeasure.UnitsOfMeasureUpdateCommand;
import com.javv.inventorySystem.application.service.product.UnitsOfMeasureService;
import com.javv.inventorySystem.domain.model.product.UnitsOfMeasure;
import com.javv.inventorySystem.presentation.shared.payload.ApiResponse;
import com.javv.inventorySystem.presentation.unitsOfMeasure.dto.UnitsOfMeasureRegisterDto;
import com.javv.inventorySystem.presentation.unitsOfMeasure.dto.UnitsOfMeasureResponseDto;
import com.javv.inventorySystem.presentation.unitsOfMeasure.dto.UnitsOfMeasureUpdateDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/uom")
public class UnitsOfMeasureController {

  @Autowired
  private UnitsOfMeasureService unitsOfMeasureService;

  @Autowired
  private UnitsOfMeasureDtoMapper unitsOfMeasureDtoMapper;

  @PostMapping("/save")
  @ResponseStatus(HttpStatus.CREATED)
  public ApiResponse<UnitsOfMeasureResponseDto> saveUom(
      @Valid @RequestBody UnitsOfMeasureRegisterDto unitsOfMeasureRegisterDto) {

    UnitsOfMeasureRegisterCommand unitsOfMeasureRegisterCommand = unitsOfMeasureDtoMapper
        .toRegisterCommandRecord(unitsOfMeasureRegisterDto);

    UnitsOfMeasure unitsOfMeasure = unitsOfMeasureService.create(unitsOfMeasureRegisterCommand);

    UnitsOfMeasureResponseDto responseDto = unitsOfMeasureDtoMapper.toResponseDto(unitsOfMeasure);

    return ApiResponse.success(
        responseDto, "Units of Measure created successfully.", HttpStatus.CREATED.value());
  }

  @PutMapping("/update/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ApiResponse<UnitsOfMeasureResponseDto> updateUom(
      @PathVariable int id,
      @Valid @RequestBody UnitsOfMeasureUpdateDto unitsOfMeasureUpdateDto) {

    UnitsOfMeasureUpdateCommand unitsOfMeasureUpdateCommand = unitsOfMeasureDtoMapper
        .toUpdateCommandRecord(unitsOfMeasureUpdateDto);

    UnitsOfMeasure unitsOfMeasure = unitsOfMeasureService.update(id, unitsOfMeasureUpdateCommand);

    UnitsOfMeasureResponseDto responseDto = unitsOfMeasureDtoMapper.toResponseDto(unitsOfMeasure);

    return ApiResponse.success(
        responseDto, "Units of Measure updated successfully.", HttpStatus.OK.value());
  }
}
