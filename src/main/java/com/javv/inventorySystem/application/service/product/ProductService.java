package com.javv.inventorySystem.application.service.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.javv.inventorySystem.domain.exception.ServiceOperationException;
import com.javv.inventorySystem.domain.model.product.Product;
import com.javv.inventorySystem.domain.model.product.ProductPackaging;
import com.javv.inventorySystem.domain.repository.ProductRepositoryInterface;

@Service
@Transactional(readOnly = true)
public class ProductService {

  @Autowired
  private ProductRepositoryInterface productRepositoryInterface;

  @Autowired
  private SupplierService supplierService;

  @Autowired
  private UnitsOfMeasureService unitsOfMeasureService;

  @Transactional
  public ProductResponseRead create(ProductRegisterCommand productRegisterCommand) {

    try {
      if (!supplierService
          .checkIfExistsBySupplierCode(productRegisterCommand.supplierCode())) {
        throw new ResourceNotFoundException(
            "Supplier does not exist with Supplier Code: " +
                productRegisterCommand.supplierCode() + ".");
      }

      if (!unitsOfMeasureService.checkIfExistsByName(productRegisterCommand.baseUnitOfMeasure())) {
        throw new ResourceNotFoundException(
            "Unit of Measure does not exists using Measure Name: " +
                productRegisterCommand.baseUnitOfMeasure() + ".");
      }

      List<String> listUomName = productRegisterCommand.listPackagingCommand()
          .stream()
          .map(packaging -> packaging.unitOfMeasure())
          .toList();

      if (!unitsOfMeasureService.doAllNamesExists(listUomName)) {
        throw new ResourceNotFoundException(
            "One or more unit of measure does not exist.");
      }

      Product product = toDomainEntity(productRegisterCommand);

      return toProductResponseRead(productRepositoryInterface.save(product));
    } catch (DataIntegrityViolationException exception) {
      throw new ServiceOperationException(
          "Product Creation Failed: The provided details conflict with an existing product.");
    } catch (ResourceNotFoundException exception) {
      throw new ServiceOperationException(
          "Product Creation Failed: " + exception.getMessage());
    }
  }

  // TODO: Modify the updateProduct method
  @Transactional
  public Product update(String sku, ProductUpdateCommand productUpdateCommand) {
    // Supplier supplier =
    // supplierService.getById(productUpdateCommand.supplierId());
    // UnitsOfMeasure unitsOfMeasure =
    // unitsOfMeasureService.getById(productUpdateCommand.baseUnitOfMeasureId());

    Product product = productRepositoryInterface
        .findBySku(sku)
        .orElseThrow(
            () -> new ResourceNotFoundException("Product was not found with SKU: " + sku + "."));

    product.setSku(productUpdateCommand.sku());
    product.setName(productUpdateCommand.name());
    // product.setSupplierId(supplier.getId());
    // product.setBaseUnitsOfMeasureId(unitsOfMeasure.getId());

    try {
      return productRepositoryInterface.update(product);
    } catch (DataIntegrityViolationException exception) {
      throw new EntityAlreadyExistsException("Product already registered.");
    }
  }

  public Product getBySku(String sku) {
    Product product = productRepositoryInterface.findBySku(sku)
        .orElseThrow(
            () -> new ResourceNotFoundException(
                "Product was not found using SKU: '" + sku + "'"));

    return product;
  }

  public boolean checkIfExistsBySku(String sku) {
    return productRepositoryInterface.existsBySku(sku);
  }

  public boolean doAllSkuExists(List<String> sku) {
    if (sku == null || sku.isEmpty()) {
      throw new IllegalArgumentException(
          "Cannot retrieve record with an empty or null list of SKUs.");
    }

    List<String> filteredList = sku
        .stream()
        .distinct()
        .toList();

    List<String> existingList = productRepositoryInterface.findExistingSkus(sku);

    return filteredList.size() == existingList.size();
  }

  public Page<ProductResponseRead> getAll(int page, int size) {
    Pageable pageable = PageRequest.of(page, size);

    Page<Product> productPage = productRepositoryInterface.findAll(pageable);

    return productPage
        .map(product -> toProductResponseRead(product));
  }

  private ProductResponseRead toProductResponseRead(Product product) {
    String supplierName = supplierService
        .getBySupplierCode(product.getSupplierCode())
        .getCompanyName();

    List<ProductPackagingResponseRead> listProductPackagingRead = new ArrayList<>();

    for (ProductPackaging packaging : product.getListPackages()) {
      listProductPackagingRead.add(
          new ProductPackagingResponseRead(
              packaging.getPackagingCode(),
              product.getSku(),
              product.getName(),
              packaging.getUnitsOfMeasureName(),
              packaging.getConversionFactor(),
              packaging.getPrice()));
    }

    ProductResponseRead responseRead = new ProductResponseRead(
        product.getSku(),
        product.getName(),
        product.getSupplierCode(),
        supplierName,
        product.getBaseUnitsOfMeasureName(),
        listProductPackagingRead);

    return responseRead;
  }

  private Product toDomainEntity(ProductRegisterCommand productRegisterCommand) {

    Product product = new Product();

    product.setSku(productRegisterCommand.sku());
    product.setName(productRegisterCommand.name());
    product.setSupplierCode(productRegisterCommand.supplierCode());
    product.setBaseUnitsOfMeasureName(productRegisterCommand.baseUnitOfMeasure());

    for (ProductPackagingRegisterCommand packaging : productRegisterCommand.listPackagingCommand()) {

      String packagingCode = product.getSku() + "-" + packaging.unitOfMeasure();

      product.addPackaging(
          packagingCode.toUpperCase(),
          packaging.unitOfMeasure(),
          packaging.conversionFactor(),
          packaging.price());
    }

    return product;
  }

}
