package com.javv.inventorySystem.presentation.product;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.javv.inventorySystem.application.command.product.ProductRegisterCommand;
import com.javv.inventorySystem.application.command.product.ProductUpdateCommand;
import com.javv.inventorySystem.application.service.product.ProductService;
import com.javv.inventorySystem.domain.model.product.Product;
import com.javv.inventorySystem.presentation.product.dto.ProductRegisterDto;
import com.javv.inventorySystem.presentation.product.dto.ProductResponseDto;
import com.javv.inventorySystem.presentation.product.dto.ProductUpdateDto;
import com.javv.inventorySystem.presentation.shared.payload.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {

  private final ProductService productService;
  private final ProductDtoMapper productDtoMapper;

  public ProductController(ProductService productService, ProductDtoMapper productDtoMapper) {
    this.productService = productService;
    this.productDtoMapper = productDtoMapper;
  }

  @PostMapping("/register")
  @ResponseStatus(HttpStatus.CREATED)
  public ApiResponse<ProductResponseDto> saveProduct(
      @Valid @RequestBody ProductRegisterDto productRegisterDto) {

    ProductRegisterCommand productRegisterCommand = productDtoMapper.toRegisterCommand(productRegisterDto);

    Product product = productService.saveProduct(productRegisterCommand);

    ProductResponseDto responseDto = productDtoMapper.toResponseDto(product);

    return ApiResponse.success(
        responseDto, "Product registered successfully.", HttpStatus.CREATED.value());
  }

  @PutMapping("/update/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ApiResponse<ProductResponseDto> updateProduct(
      @PathVariable Long id, @Valid @RequestBody ProductUpdateDto productUpdateDto) {

    ProductUpdateCommand productUpdateCommand = productDtoMapper.toUpdateCommand(productUpdateDto);

    Product product = productService.updateProduct(id, productUpdateCommand);

    ProductResponseDto responseDto = productDtoMapper.toResponseDto(product);

    return ApiResponse.success(
        responseDto,
        "Product updated successfully.",
        HttpStatus.OK.value());
  }
}
