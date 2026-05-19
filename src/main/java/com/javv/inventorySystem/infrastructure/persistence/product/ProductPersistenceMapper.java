package com.javv.inventorySystem.infrastructure.persistence.product;

import org.springframework.stereotype.Component;

import com.javv.inventorySystem.domain.model.product.Product;
import com.javv.inventorySystem.infrastructure.persistence.supplier.SupplierPersistenceMapper;
import com.javv.inventorySystem.infrastructure.persistence.unitsOfMeasure.UnitsOfMeasurePersistenceMapper;

@Component
public class ProductPersistenceMapper {

  private final SupplierPersistenceMapper supplierPersistenceMapper;
  private final UnitsOfMeasurePersistenceMapper unitsOfMeasurePersistenceMapper;

  public ProductPersistenceMapper(
      SupplierPersistenceMapper supplierPersistenceMapper,
      UnitsOfMeasurePersistenceMapper unitsOfMeasurePersistenceMapper) {
    this.supplierPersistenceMapper = supplierPersistenceMapper;
    this.unitsOfMeasurePersistenceMapper = unitsOfMeasurePersistenceMapper;
  }

  public ProductJpaEntity toJpaEntity(Product product) {
    ProductJpaEntity productJpaEntity = new ProductJpaEntity();

    productJpaEntity.setSku(product.getSku());
    productJpaEntity.setName(product.getName());
    productJpaEntity.setSupplier(
        supplierPersistenceMapper.toJpaEntity(
            product.getSupplier()));
    productJpaEntity.setBaseUom(
        unitsOfMeasurePersistenceMapper.toJpaEntity(
            product.getBaseUnitsOfMeasure()));

    return productJpaEntity;
  }

  public Product toDomainEntity(ProductJpaEntity productJpaEntity) {

    Product product = new Product();

    product.setSku(productJpaEntity.getSku());
    product.setName(productJpaEntity.getName());
    product.setSupplier(supplierPersistenceMapper.toDomainEntity(
        productJpaEntity.getSupplier()));
    product.setUnitsOfMeasure(unitsOfMeasurePersistenceMapper.toDomainEntity(
        productJpaEntity.getBaseUnitOfMeasure()));
    product.setCreatedAt(productJpaEntity.getCreatedAt());
    product.setUpdatedAt(productJpaEntity.getUpdatedAt());

    return product;
  }
}
