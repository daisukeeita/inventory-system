package com.javv.inventorySystem.application.service.productPackaging;

import java.util.Objects;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javv.inventorySystem.application.command.productPackaging.ProductPackagingRegisterCommand;
import com.javv.inventorySystem.application.command.productPackaging.ProductPackagingUpdateCommand;
import com.javv.inventorySystem.application.service.product.ProductService;
import com.javv.inventorySystem.application.service.product.UnitsOfMeasureService;
import com.javv.inventorySystem.domain.exception.EntityAlreadyExistsException;
import com.javv.inventorySystem.domain.model.product.Product;
import com.javv.inventorySystem.domain.model.product.ProductPackaging;
import com.javv.inventorySystem.domain.model.product.UnitsOfMeasure;
import com.javv.inventorySystem.domain.repository.ProductPackagingRepositoryInterface;

@Service
@Transactional(readOnly = true)
public class ProductPackagingService {

  private final ProductService productService;
  private final UnitsOfMeasureService unitsOfMeasureService;
  private final ProductPackagingRepositoryInterface productPackagingRepositoryInterface;

  public ProductPackagingService(
      ProductService productService,
      UnitsOfMeasureService unitsOfMeasureService,
      ProductPackagingRepositoryInterface productPackagingRepositoryInterface) {
    this.productService = productService;
    this.unitsOfMeasureService = unitsOfMeasureService;
    this.productPackagingRepositoryInterface = productPackagingRepositoryInterface;
  }

  @Transactional
  public ProductPackaging savePackaging(
      ProductPackagingRegisterCommand productPackagingRegisterCommand) {

    UnitsOfMeasure unitsOfMeasure =
        unitsOfMeasureService.getByName(productPackagingRegisterCommand.unitOfMeasure());

    Product product = productService.getBySku(productPackagingRegisterCommand.sku());

    ProductPackaging productPackaging =
        toRegisterDomain(productPackagingRegisterCommand, unitsOfMeasure, product);

    try {
      return productPackagingRepositoryInterface.save(productPackaging);
    } catch (DataIntegrityViolationException exception) {
      throw new EntityAlreadyExistsException(
          "Product Packaging Service: Product Packaging already exists.");
    }
  }

  @Transactional
  public ProductPackaging updatePackaging(
      ProductPackagingUpdateCommand productPackagingUpdateCommand) {
    return null;
  }

  private ProductPackaging toRegisterDomain(
      ProductPackagingRegisterCommand productPackagingRegisterCommand,
      UnitsOfMeasure unitsOfMeasure,
      Product product) {

    Objects.requireNonNull(
        productPackagingRegisterCommand,
        "Product Packaging Service: Cannot map a null ProductPackagingRegisterCommand to a"
            + " Domain.");

    Objects.requireNonNull(
        unitsOfMeasure,
        "Product Packaging Service: Units of Measure cannot be null for ProductPackaging Domain.");

    Objects.requireNonNull(
        product, "Product Packaging Service: Product cannot be null for ProductPackaging Domain.");

    ProductPackaging productPackaging = new ProductPackaging();
    productPackaging.setPackagingCode(productPackagingRegisterCommand.packagingCode());
    productPackaging.setProduct(product);
    productPackaging.setUnitsOfMeasure(unitsOfMeasure);
    productPackaging.setConversionFactor(productPackagingRegisterCommand.conversionFactor());
    productPackaging.setPrice(productPackagingRegisterCommand.price());

    return productPackaging;
  }
}
