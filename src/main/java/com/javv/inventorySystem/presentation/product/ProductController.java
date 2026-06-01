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
import com.javv.inventorySystem.application.service.product.UnitsOfMeasureService;
import com.javv.inventorySystem.application.service.supplier.SupplierService;
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
  private final SupplierService supplierService;
  private final ProductDtoMapper productDtoMapper;
  private final UnitsOfMeasureService unitsOfMeasureService;

  public ProductController(
      ProductService productService,
      SupplierService supplierService,
      ProductDtoMapper productDtoMapper,
      UnitsOfMeasureService unitsOfMeasureService) {
    this.productService = productService;
    this.supplierService = supplierService;
    this.productDtoMapper = productDtoMapper;
    this.unitsOfMeasureService = unitsOfMeasureService;
  }

  @PostMapping("/register")
  @ResponseStatus(HttpStatus.CREATED)
  public ApiResponse<ProductResponseDto> saveProduct(
      @Valid @RequestBody ProductRegisterDto productRegisterDto) {

    ProductRegisterCommand productRegisterCommand = productDtoMapper.toRegisterCommand(productRegisterDto);

    Product product = productService.saveProduct(productRegisterCommand);
    String supplierName = supplierService
        .getById(product.getSupplierId()).getCompanyName();
    String uomName = unitsOfMeasureService
        .getById(product.getBaseUnitsOfMeasureId()).getName();

    ProductResponseDto responseDto = productDtoMapper
        .toResponseDto(product, supplierName, uomName);

    return ApiResponse.success(
        responseDto,
        "Product registered successfully.",
        HttpStatus.CREATED.value());
  }

  @PutMapping("/update/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ApiResponse<ProductResponseDto> updateProduct(
      @PathVariable Long id, @Valid @RequestBody ProductUpdateDto productUpdateDto) {

    ProductUpdateCommand productUpdateCommand = productDtoMapper.toUpdateCommand(productUpdateDto);

    Product product = productService.updateProduct(id, productUpdateCommand);
    String supplierName = supplierService
        .getById(product.getSupplierId()).getCompanyName();
    String uomName = unitsOfMeasureService
        .getById(product.getBaseUnitsOfMeasureId()).getName();

    ProductResponseDto responseDto = productDtoMapper
        .toResponseDto(product, supplierName, uomName);

    return ApiResponse.success(
        responseDto,
        "Product updated successfully.",
        HttpStatus.OK.value());
  }
}
