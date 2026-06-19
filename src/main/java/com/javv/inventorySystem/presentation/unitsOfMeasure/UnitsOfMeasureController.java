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
import jakarta.validation.constraints.NotBlank;

@RestController
@RequestMapping("api/v1/uom")
public class UnitsOfMeasureController {

  @Autowired
  private UnitsOfMeasureService unitsOfMeasureService;

  @Autowired
  private UnitsOfMeasureDtoMapper unitsOfMeasureDtoMapper;

  @PostMapping("/create")
  @ResponseStatus(HttpStatus.CREATED)
  public ApiResponse<UnitsOfMeasureResponseDto> create(
      @Valid @RequestBody UnitsOfMeasureRegisterDto unitsOfMeasureRegisterDto) {

    UnitsOfMeasureRegisterCommand unitsOfMeasureRegisterCommand = unitsOfMeasureDtoMapper
        .toRegisterCommandRecord(unitsOfMeasureRegisterDto);

    UnitsOfMeasure unitsOfMeasure = unitsOfMeasureService.create(unitsOfMeasureRegisterCommand);

    UnitsOfMeasureResponseDto responseDto = unitsOfMeasureDtoMapper.toResponseDto(unitsOfMeasure);

    return ApiResponse.success(
        responseDto,
        "Units of Measure created successfully.",
        HttpStatus.CREATED.value());
  }

  @PutMapping("/update/{name}")
  @ResponseStatus(HttpStatus.OK)
  public ApiResponse<UnitsOfMeasureResponseDto> update(
      @PathVariable @NotBlank(message = "Query Parameter: Unit of Measure Name cannot be blank.") String name,
      @Valid @RequestBody UnitsOfMeasureUpdateDto unitsOfMeasureUpdateDto) {

    UnitsOfMeasureUpdateCommand unitsOfMeasureUpdateCommand = unitsOfMeasureDtoMapper
        .toUpdateCommandRecord(unitsOfMeasureUpdateDto);

    UnitsOfMeasure unitsOfMeasure = unitsOfMeasureService.update(name, unitsOfMeasureUpdateCommand);

    UnitsOfMeasureResponseDto responseDto = unitsOfMeasureDtoMapper.toResponseDto(unitsOfMeasure);

    return ApiResponse.success(
        responseDto,
        "Units of Measure updated successfully.",
        HttpStatus.OK.value());
  }
}
