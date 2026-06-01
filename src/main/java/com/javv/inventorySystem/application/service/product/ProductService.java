package com.javv.inventorySystem.application.service.product;

import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javv.inventorySystem.application.command.product.ProductRegisterCommand;
import com.javv.inventorySystem.application.command.product.ProductUpdateCommand;
import com.javv.inventorySystem.application.service.supplier.SupplierService;
import com.javv.inventorySystem.domain.exception.EntityAlreadyExistsException;
import com.javv.inventorySystem.domain.exception.ResourceNotFoundException;
import com.javv.inventorySystem.domain.model.product.Product;
import com.javv.inventorySystem.domain.model.product.UnitsOfMeasure;
import com.javv.inventorySystem.domain.model.supplier.Supplier;
import com.javv.inventorySystem.domain.repository.ProductRepositoryInterface;

@Service
@Transactional(readOnly = true)
public class ProductService {

  private final ProductRepositoryInterface productRepositoryInterface;
  private final SupplierService supplierService;
  private final UnitsOfMeasureService unitsOfMeasureService;

  public ProductService(
      ProductRepositoryInterface productRepositoryInterface,
      SupplierService supplierService,
      UnitsOfMeasureService unitsOfMeasureService) {
    this.productRepositoryInterface = productRepositoryInterface;
    this.supplierService = supplierService;
    this.unitsOfMeasureService = unitsOfMeasureService;
  }

  @Transactional
  public Product saveProduct(ProductRegisterCommand productRegisterCommand) {

    Supplier supplier = supplierService
        .getById(productRegisterCommand.supplierId());

    UnitsOfMeasure unitsOfMeasure = unitsOfMeasureService
        .getById(productRegisterCommand.baseUnitOfMeasureId());

    Product product = new Product();
    product.setSku(productRegisterCommand.sku());
    product.setName(productRegisterCommand.name());
    product.setSupplierId(supplier.getId());
    product.setUnitsOfMeasureId(unitsOfMeasure.getId());

    try {
      return productRepositoryInterface.save(product);
    } catch (DataIntegrityViolationException exception) {
      throw new EntityAlreadyExistsException(
          "Product Service: " + exception.getMessage());
    }
  }

  @Transactional
  public Product updateProduct(Long id, ProductUpdateCommand productUpdateCommand) {
    Supplier supplier = supplierService.getById(productUpdateCommand.supplierId());
    UnitsOfMeasure unitsOfMeasure = unitsOfMeasureService.getById(productUpdateCommand.baseUnitOfMeasureId());

    Product product = productRepositoryInterface
        .getById(id)
        .orElseThrow(
            () -> new ResourceNotFoundException("Product was not found using id: '" + id + "'"));

    product.setSku(productUpdateCommand.sku());
    product.setName(productUpdateCommand.name());
    product.setSupplierId(supplier.getId());
    product.setUnitsOfMeasureId(unitsOfMeasure.getId());

    try {
      return productRepositoryInterface.update(product);
    } catch (DataIntegrityViolationException exception) {
      throw new EntityAlreadyExistsException("Product already registered.");
    }
  }

  public Product getBySku(String sku) {
    Optional<Product> optionalProduct = productRepositoryInterface.getBySku(sku);
    Product product = optionalProduct.orElseThrow(
        () -> new ResourceNotFoundException(
            "Product Service: Product was not found using SKU: '" + sku + "'"));

    return product;
  }

  public Product getById(Long id) {
    Optional<Product> optionalProduct = productRepositoryInterface.getById(id);
    Product product = optionalProduct.orElseThrow(
        () -> new ResourceNotFoundException(
            "Product Service: Product was not found using ID: '" + id + "'"));

    return product;
  }
}
