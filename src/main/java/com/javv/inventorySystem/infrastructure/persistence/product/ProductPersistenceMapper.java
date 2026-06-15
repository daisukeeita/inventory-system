package com.javv.inventorySystem.infrastructure.persistence.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javv.inventorySystem.domain.exception.ObjectMappingException;
import com.javv.inventorySystem.domain.model.product.Product;
import com.javv.inventorySystem.domain.model.product.ProductPackaging;
import com.javv.inventorySystem.infrastructure.persistence.productPackaging.ProductPackagingJpaEntity;
import com.javv.inventorySystem.infrastructure.persistence.supplier.SupplierJpaEntity;
import com.javv.inventorySystem.infrastructure.persistence.supplier.SupplierJpaRepository;
import com.javv.inventorySystem.infrastructure.persistence.unitsOfMeasure.UnitsOfMeasureJpaEntity;
import com.javv.inventorySystem.infrastructure.persistence.unitsOfMeasure.UnitsOfMeasureJpaRepository;

@Component
public class ProductPersistenceMapper {

  @Autowired
  private SupplierJpaRepository supplierJpaRepository;

  @Autowired
  private UnitsOfMeasureJpaRepository unitsOfMeasureJpaRepository;

  public ProductJpaEntity toJpaEntity(Product product) {

    if (product == null) {
      throw new ObjectMappingException(
          "Product Persistence Mapper: Cannot map a null Product to a JPA Entity.");
    }

    SupplierJpaEntity supplierJpaEntity = supplierJpaRepository
        .getReferenceById(product.getSupplierId());

    UnitsOfMeasureJpaEntity unitsOfMeasureJpaEntity = unitsOfMeasureJpaRepository
        .getReferenceById(product.getBaseUnitsOfMeasureId());

    ProductJpaEntity jpaEntity = new ProductJpaEntity();
    jpaEntity.setSku(product.getSku());
    jpaEntity.setName(product.getName());
    jpaEntity.setSupplier(supplierJpaEntity);
    jpaEntity.setBaseUom(unitsOfMeasureJpaEntity);

    for (ProductPackaging packaging : product.getListPackages()) {

      UnitsOfMeasureJpaEntity unitMeasure = unitsOfMeasureJpaRepository
          .getReferenceById(packaging.getUnitsOfMeasureId());

      jpaEntity.addPackaging(
          packaging.getPackagingCode(),
          unitMeasure,
          packaging.getConversionFactor(),
          packaging.getPrice());
    }

    return jpaEntity;
  }

  public Product toDomainEntity(ProductJpaEntity productJpaEntity) {

    if (productJpaEntity == null) {
      throw new ObjectMappingException(
          "Product Persistence Mapper: Cannot map a null ProductJpaEntity to a Domain Entity.");
    }

    Product domainEntity = new Product();
    domainEntity.setId(productJpaEntity.getId());
    domainEntity.setSku(productJpaEntity.getSku());
    domainEntity.setName(productJpaEntity.getName());
    domainEntity.setSupplierId(productJpaEntity.getSupplier().getId());
    domainEntity.setBaseUnitsOfMeasureId(productJpaEntity.getBaseUnitOfMeasure().getId());
    domainEntity.setCreatedAt(productJpaEntity.getCreatedAt());
    domainEntity.setUpdatedAt(productJpaEntity.getUpdatedAt());

    for (ProductPackagingJpaEntity jpaPackaging : productJpaEntity.getListPackages()) {
      ProductPackaging domainPackaging = domainEntity.addPackaging(
          jpaPackaging.getPackagingCode(),
          jpaPackaging.getUnitsOfMeasure().getId(),
          jpaPackaging.getConversionFactor(),
          jpaPackaging.getPrice());

      domainPackaging.setId(jpaPackaging.getId());
    }

    return domainEntity;
  }
}
