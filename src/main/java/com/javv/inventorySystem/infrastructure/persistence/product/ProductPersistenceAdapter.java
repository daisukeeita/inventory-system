package com.javv.inventorySystem.infrastructure.persistence.product;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.javv.inventorySystem.domain.model.product.Product;
import com.javv.inventorySystem.domain.model.product.ProductPackaging;
import com.javv.inventorySystem.domain.repository.ProductRepositoryInterface;
import com.javv.inventorySystem.infrastructure.persistence.productPackaging.ProductPackagingJpaEntity;
import com.javv.inventorySystem.infrastructure.persistence.supplier.SupplierJpaEntity;
import com.javv.inventorySystem.infrastructure.persistence.supplier.SupplierJpaRepository;
import com.javv.inventorySystem.infrastructure.persistence.unitsOfMeasure.UnitsOfMeasureJpaEntity;
import com.javv.inventorySystem.infrastructure.persistence.unitsOfMeasure.UnitsOfMeasureJpaRepository;

@Repository
public class ProductPersistenceAdapter implements ProductRepositoryInterface {
  private final ProductJpaRepository productJpaRepository;
  private final SupplierJpaRepository supplierJpaRepository;
  private final ProductPersistenceMapper productPersistenceMapper;
  private final UnitsOfMeasureJpaRepository unitsOfMeasureJpaRepository;

  public ProductPersistenceAdapter(
      ProductJpaRepository productJpaRepository,
      SupplierJpaRepository supplierJpaRepository,
      ProductPersistenceMapper productPersistenceMapper,
      UnitsOfMeasureJpaRepository unitsOfMeasureJpaRepository) {
    this.productJpaRepository = productJpaRepository;
    this.supplierJpaRepository = supplierJpaRepository;
    this.productPersistenceMapper = productPersistenceMapper;
    this.unitsOfMeasureJpaRepository = unitsOfMeasureJpaRepository;

  }

  @Override
  public Product save(Product product) {

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
    // ProductJpaEntity productJpaEntity = productPersistenceMapper
    // .toJpaEntity(product, supplierJpaEntity, unitsOfMeasureJpaEntity);

    ProductJpaEntity savedProduct = productJpaRepository.saveAndFlush(jpaEntity);

    Product domainEntity = new Product();
    domainEntity.setId(savedProduct.getId());
    domainEntity.setSku(savedProduct.getSku());
    domainEntity.setName(savedProduct.getName());
    domainEntity.setSupplierId(savedProduct.getSupplier().getId());
    domainEntity.setBaseUnitsOfMeasureId(savedProduct.getBaseUnitOfMeasure().getId());
    domainEntity.setCreatedAt(savedProduct.getCreatedAt());
    domainEntity.setUpdatedAt(savedProduct.getUpdatedAt());

    for (ProductPackagingJpaEntity jpaPackaging : savedProduct.getProductPackages()) {
      ProductPackaging domainPackaging = domainEntity.addPackaging(
          jpaPackaging.getPackagingCode(),
          jpaPackaging.getUnitsOfMeasure().getId(),
          jpaPackaging.getConversionFactor(),
          jpaPackaging.getPrice());

      domainPackaging.setId(jpaPackaging.getId());
    }

    return domainEntity;

  }

  @Override
  public Product update(Product product) {
    SupplierJpaEntity supplierJpaEntity = supplierJpaRepository
        .getReferenceById(product.getSupplierId());

    UnitsOfMeasureJpaEntity unitsOfMeasureJpaEntity = unitsOfMeasureJpaRepository
        .getReferenceById(product.getBaseUnitsOfMeasureId());

    ProductJpaEntity productJpaEntity = productPersistenceMapper
        .toJpaEntity(product, supplierJpaEntity, unitsOfMeasureJpaEntity);

    ProductJpaEntity savedProduct = productJpaRepository.saveAndFlush(productJpaEntity);

    return productPersistenceMapper.toDomainEntity(savedProduct);
  }

  @Override
  public Optional<Product> getBySku(String sku) {

    Optional<ProductJpaEntity> jpaEntity = productJpaRepository.findBySku(sku);

    return jpaEntity.map(entity -> productPersistenceMapper.toDomainEntity(entity));
  }

  @Override
  public Optional<Product> getById(Long id) {

    Optional<ProductJpaEntity> jpaEntity = productJpaRepository.findById(id);

    return jpaEntity.map(entity -> productPersistenceMapper.toDomainEntity(entity));
  }
}
