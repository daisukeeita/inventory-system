package com.javv.inventorySystem.application.service.product;

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

    Supplier supplier = supplierService.getByName(productRegisterCommand.supplier());

    UnitsOfMeasure unitsOfMeasure = unitsOfMeasureService.getByName(productRegisterCommand.baseUnitOfMeasure());

    Product product = new Product();
    product.setSku(productRegisterCommand.sku());
    product.setName(productRegisterCommand.name());
    product.setSupplier(supplier);
    product.setUnitsOfMeasure(unitsOfMeasure);

    try {
      return productRepositoryInterface.save(product);
    } catch (DataIntegrityViolationException exception) {
      throw new EntityAlreadyExistsException("Product already registered.");
    }
  }

  @Transactional
  public Product updateProduct(Long id, ProductUpdateCommand productUpdateCommand) {
    Supplier supplier = supplierService.getByName(
        productUpdateCommand.supplier());
    UnitsOfMeasure unitsOfMeasure = unitsOfMeasureService.getByName(
        productUpdateCommand.baseUnitOfMeasure());

    Product product = productRepositoryInterface
        .getById(id)
        .orElseThrow(
            () -> new ResourceNotFoundException("Product was not found using id: '" + id + "'"));

    product.setSku(productUpdateCommand.sku());
    product.setName(productUpdateCommand.name());
    product.setSupplier(supplier);
    product.setUnitsOfMeasure(unitsOfMeasure);

    try {
      return productRepositoryInterface.update(product);
    } catch (DataIntegrityViolationException exception) {
      throw new EntityAlreadyExistsException("Product already registered.");
    }
  }
}
