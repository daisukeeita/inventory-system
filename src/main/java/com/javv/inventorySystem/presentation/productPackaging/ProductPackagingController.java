package com.javv.inventorySystem.presentation.productPackaging;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.javv.inventorySystem.application.command.productPackaging.ProductPackagingRegisterCommand;
import com.javv.inventorySystem.application.service.productPackaging.ProductPackagingService;
import com.javv.inventorySystem.domain.model.product.ProductPackaging;
import com.javv.inventorySystem.presentation.productPackaging.dto.ProductPackagingRegisterDto;
import com.javv.inventorySystem.presentation.productPackaging.dto.ProductPackagingResponseDto;
import com.javv.inventorySystem.presentation.shared.payload.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/packaging")
public class ProductPackagingController {

  private final ProductPackagingService productPackagingService;
  private final ProductPackagingDtoMapper productPackagingDtoMapper;

  public ProductPackagingController(
      ProductPackagingService productPackagingService,
      ProductPackagingDtoMapper productPackagingDtoMapper) {
    this.productPackagingService = productPackagingService;
    this.productPackagingDtoMapper = productPackagingDtoMapper;
  }

  @PostMapping("/register")
  @ResponseStatus(HttpStatus.CREATED)
  public ApiResponse<ProductPackagingResponseDto> savePackaging(
      @Valid @RequestBody ProductPackagingRegisterDto productPackagingRegisterDto) {

    ProductPackagingRegisterCommand registerCommand =
        productPackagingDtoMapper.toRegisterCommand(productPackagingRegisterDto);

    ProductPackaging productPackaging = productPackagingService.savePackaging(registerCommand);

    ProductPackagingResponseDto responseDto =
        productPackagingDtoMapper.toResponseDto(productPackaging);

    return ApiResponse.success(
        responseDto, "Product Packaging registered successfully.", HttpStatus.CREATED.value());
  }
}
