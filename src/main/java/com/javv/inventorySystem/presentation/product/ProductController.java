package com.javv.inventorySystem.presentation.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.javv.inventorySystem.application.command.product.ProductRegisterCommand;
import com.javv.inventorySystem.application.command.product.ProductResponseRead;
import com.javv.inventorySystem.application.service.product.ProductService;
import com.javv.inventorySystem.presentation.product.dto.ProductRegisterDto;
import com.javv.inventorySystem.presentation.product.dto.ProductResponseDto;
import com.javv.inventorySystem.presentation.shared.payload.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {

  @Autowired
  private ProductService productService;

  @Autowired
  private ProductDtoMapper productDtoMapper;

  @PostMapping("/register")
  @ResponseStatus(HttpStatus.CREATED)
  public ApiResponse<ProductResponseDto> saveProduct(
      @Valid @RequestBody ProductRegisterDto productRegisterDto) {

    ProductRegisterCommand productRegisterCommand = productDtoMapper
        .toRegisterCommand(productRegisterDto);

    ProductResponseRead productResponseRead = productService.create(productRegisterCommand);

    ProductResponseDto responseDto = productDtoMapper
        .toResponseDto(productResponseRead);

    return ApiResponse.success(
        responseDto,
        "Product registered successfully.",
        HttpStatus.CREATED.value());
  }

  // TODO: Implement a pagination list of Products
  @GetMapping("/getAll/")
  public ApiResponse<PagedModel<ProductResponseDto>> getAllProduct(
      @RequestParam(value = "page", defaultValue = "0") int page,
      @RequestParam(value = "size", defaultValue = "10") int size) {

    Page<ProductResponseRead> pageProduct = productService.getAll(page, size);

    Page<ProductResponseDto> pageProductDto = pageProduct.map(
        productRead -> productDtoMapper.toResponseDto(productRead));

    PagedModel<ProductResponseDto> pagedModelProductDto = new PagedModel<>(pageProductDto);

    return ApiResponse.success(
        pagedModelProductDto,
        "Paged Product retrieved successfully",
        HttpStatus.FOUND.value());
  }

  // @PutMapping("/update/{id}")
  // @ResponseStatus(HttpStatus.OK)
  // public ApiResponse<ProductResponseDto> updateProduct(
  // @PathVariable Long id, @Valid @RequestBody ProductUpdateDto productUpdateDto)
  // {
  //
  // ProductUpdateCommand productUpdateCommand =
  // productDtoMapper.toUpdateCommand(productUpdateDto);
  //
  // Product product = productService.updateProduct(id, productUpdateCommand);
  // String supplierName = supplierService
  // .getById(product.getSupplierId()).getCompanyName();
  // String uomName = unitsOfMeasureService
  // .getById(product.getBaseUnitsOfMeasureId()).getName();
  //
  // ProductResponseDto responseDto = productDtoMapper
  // .toResponseDto(product, supplierName, uomName);
  //
  // return ApiResponse.success(
  // responseDto,
  // "Product updated successfully.",
  // HttpStatus.OK.value());
  // }
}
