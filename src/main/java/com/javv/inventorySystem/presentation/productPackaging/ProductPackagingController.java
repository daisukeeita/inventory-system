package com.javv.inventorySystem.presentation.productPackaging;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javv.inventorySystem.application.service.productPackaging.ProductPackagingService;

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

  // @PostMapping("/register")
  // @ResponseStatus(HttpStatus.CREATED)
  // public ApiResponse<ProductPackagingResponseDto> savePackaging(
  // @Valid @RequestBody ProductPackagingRegisterDto productPackagingRegisterDto)
  // {
  //
  // ProductPackagingRegisterCommand registerCommand =
  // productPackagingDtoMapper.toRegisterCommand(productPackagingRegisterDto);
  //
  // ProductPackaging productPackaging =
  // productPackagingService.savePackaging(registerCommand);
  //
  // ProductPackagingResponseDto responseDto =
  // productPackagingDtoMapper.toResponseDto(productPackaging);
  //
  // return ApiResponse.success(
  // responseDto, "Product Packaging registered successfully.",
  // HttpStatus.CREATED.value());
  // }
}
