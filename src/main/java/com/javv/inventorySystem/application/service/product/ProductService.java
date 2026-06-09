package com.javv.inventorySystem.application.service.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javv.inventorySystem.application.command.product.ProductRegisterCommand;
import com.javv.inventorySystem.application.command.product.ProductResponseRead;
import com.javv.inventorySystem.application.command.product.ProductUpdateCommand;
import com.javv.inventorySystem.application.command.productPackaging.ProductPackagingRegisterCommand;
import com.javv.inventorySystem.application.command.productPackaging.ProductPackagingResponseRead;
import com.javv.inventorySystem.application.service.supplier.SupplierService;
import com.javv.inventorySystem.domain.exception.EntityAlreadyExistsException;
import com.javv.inventorySystem.domain.exception.ResourceNotFoundException;
import com.javv.inventorySystem.domain.model.product.Product;
import com.javv.inventorySystem.domain.model.product.ProductPackaging;
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
  public ProductResponseRead saveProduct(ProductRegisterCommand productRegisterCommand) {

    Product product = toDomainEntity(productRegisterCommand);

    try {
      return toProductResponseRead(productRepositoryInterface.save(product));
    } catch (DataIntegrityViolationException exception) {
      throw new EntityAlreadyExistsException(
          "Product Service: " + exception.getMessage());
    }
  }

  // TODO: Modify the updateProduct method
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
    product.setBaseUnitsOfMeasureId(unitsOfMeasure.getId());

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

  public Page<Product> getPageableProduct(int page, int size) {
    Pageable pageable = PageRequest.of(page, size);

    return productRepositoryInterface.getPageableProduct(pageable);
  }

  public boolean checkIfExistsById(Long id) {
    return productRepositoryInterface.existsById(id);
  }

  private ProductResponseRead toProductResponseRead(Product product) {
    String supplierName = supplierService.getById(
        product.getSupplierId()).getCompanyName();
    String baseUnitOfMeasureName = unitsOfMeasureService.getById(
        product.getBaseUnitsOfMeasureId()).getName();

    List<ProductPackagingResponseRead> listProductPackagingRead = new ArrayList<>();

    for (ProductPackaging packaging : product.getListPackages()) {
      String productSku = getById(packaging.getProductId()).getSku();
      String unitOfMeasureName = unitsOfMeasureService.getById(
          packaging.getUnitsOfMeasureId()).getName();

      listProductPackagingRead.add(
          new ProductPackagingResponseRead(
              packaging.getId(),
              packaging.getPackagingCode(),
              productSku,
              unitOfMeasureName,
              packaging.getConversionFactor(),
              packaging.getPrice()));
    }

    ProductResponseRead responseRead = new ProductResponseRead(
        product.getId(),
        product.getSku(),
        product.getName(),
        product.getSupplierId(),
        supplierName,
        product.getBaseUnitsOfMeasureId(),
        baseUnitOfMeasureName,
        listProductPackagingRead);

    return responseRead;
  }

  private Product toDomainEntity(ProductRegisterCommand productRegisterCommand) {
    Supplier supplier = supplierService
        .getById(productRegisterCommand.supplierId());

    UnitsOfMeasure unitsOfMeasure = unitsOfMeasureService
        .getById(productRegisterCommand.baseUnitOfMeasureId());

    Product product = new Product();
    product.setSku(productRegisterCommand.sku());
    product.setName(productRegisterCommand.name());
    product.setSupplierId(supplier.getId());
    product.setBaseUnitsOfMeasureId(unitsOfMeasure.getId());

    for (ProductPackagingRegisterCommand packaging : productRegisterCommand.listPackagingCommand()) {
      UnitsOfMeasure unitMeasure = unitsOfMeasureService.getById(
          packaging.unitOfMeasureId());

      product.addPackaging(
          packaging.packagingCode(),
          unitMeasure.getId(),
          packaging.conversionFactor(),
          packaging.price());
    }

    return product;
  }

}
