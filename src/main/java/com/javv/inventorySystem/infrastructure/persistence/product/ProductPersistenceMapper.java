package com.javv.inventorySystem.infrastructure.persistence.product;

import org.springframework.stereotype.Component;

import com.javv.inventorySystem.domain.model.product.Product;
import com.javv.inventorySystem.infrastructure.persistence.supplier.SupplierJpaEntity;
import com.javv.inventorySystem.infrastructure.persistence.supplier.SupplierPersistenceMapper;
import com.javv.inventorySystem.infrastructure.persistence.unitsOfMeasure.UnitsOfMeasureJpaEntity;
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

  public ProductJpaEntity toJpaEntity(Product product, SupplierJpaEntity supplierJpaEntity,
      UnitsOfMeasureJpaEntity unitsOfMeasureJpaEntity) {
    ProductJpaEntity productJpaEntity = new ProductJpaEntity();

    productJpaEntity.setId(product.getId());
    productJpaEntity.setSku(product.getSku());
    productJpaEntity.setName(product.getName());
    productJpaEntity.setSupplier(supplierJpaEntity);
    productJpaEntity.setBaseUom(unitsOfMeasureJpaEntity);

    return productJpaEntity;
  }

  public Product toDomainEntity(ProductJpaEntity productJpaEntity) {

    Product product = new Product();

    product.setId(productJpaEntity.getId());
    product.setSku(productJpaEntity.getSku());
    product.setName(productJpaEntity.getName());
    product.setSupplierId(productJpaEntity.getSupplier().getId());
    product.setBaseUnitsOfMeasureId(
        productJpaEntity.getBaseUnitOfMeasure().getId());
    product.setCreatedAt(productJpaEntity.getCreatedAt());
    product.setUpdatedAt(productJpaEntity.getUpdatedAt());

    return product;
  }
}
