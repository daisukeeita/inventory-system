package com.javv.inventorySystem.application.service.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import com.javv.inventorySystem.domain.model.product.UnitsOfMeasure;
import com.javv.inventorySystem.domain.model.supplier.Supplier;
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
      if (!supplierService.checkIfExistsById(productRegisterCommand.supplierId())) {
        throw new ResourceNotFoundException(
            "Supplier does not exist using id: " + productRegisterCommand.supplierId() + ".");
      }

      if (!unitsOfMeasureService.checkIfExistsById(productRegisterCommand.baseUnitOfMeasureId())) {
        throw new ResourceNotFoundException(
            "Unit of Measure does not exists using id: " + productRegisterCommand.baseUnitOfMeasureId() + ".");
      }

      List<Integer> listUomId = productRegisterCommand.listPackagingCommand()
          .stream()
          .map(packaging -> packaging.unitOfMeasureId())
          .toList();

      if (!unitsOfMeasureService.allIdExists(listUomId)) {
        throw new ResourceNotFoundException(
            "One or more unit of measure IDs do not exists.");
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
  public Product update(Long id, ProductUpdateCommand productUpdateCommand) {
    Supplier supplier = supplierService.getById(productUpdateCommand.supplierId());
    UnitsOfMeasure unitsOfMeasure = unitsOfMeasureService.getById(productUpdateCommand.baseUnitOfMeasureId());

    Product product = productRepositoryInterface
        .findById(id)
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
    Optional<Product> optionalProduct = productRepositoryInterface.findBySku(sku);
    Product product = optionalProduct.orElseThrow(
        () -> new ResourceNotFoundException(
            "Product was not found using SKU: '" + sku + "'"));

    return product;
  }

  public Product getById(Long id) {
    Optional<Product> optionalProduct = productRepositoryInterface.findById(id);
    Product product = optionalProduct.orElseThrow(
        () -> new ResourceNotFoundException(
            "Product was not found using ID: '" + id + "'"));

    return product;
  }

  public List<Product> getAllById(List<Long> id) {
    return productRepositoryInterface.findAllById(id);
  }

  public Page<Product> getAll(int page, int size) {
    Pageable pageable = PageRequest.of(page, size);

    return productRepositoryInterface.findAll(pageable);
  }

  public boolean checkIfExistsById(Long id) {
    return productRepositoryInterface.existsById(id);
  }

  public boolean allIdsExists(List<Long> listId) {
    if (listId == null || listId.isEmpty()) {
      return false;
    }

    Long existCount = productRepositoryInterface.countByIdIn(listId);
    return existCount == listId.size();
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

    Product product = new Product();
    product.setSku(productRegisterCommand.sku());
    product.setName(productRegisterCommand.name());
    product.setSupplierId(productRegisterCommand.supplierId());
    product.setBaseUnitsOfMeasureId(productRegisterCommand.baseUnitOfMeasureId());

    for (ProductPackagingRegisterCommand packaging : productRegisterCommand.listPackagingCommand()) {
      product.addPackaging(
          packaging.packagingCode(),
          packaging.unitOfMeasureId(),
          packaging.conversionFactor(),
          packaging.price());
    }

    return product;
  }

}
