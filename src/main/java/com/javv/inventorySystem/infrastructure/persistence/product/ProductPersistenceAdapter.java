package com.javv.inventorySystem.infrastructure.persistence.product;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    ProductJpaEntity jpaEntity = toJpaEntity(product);

    ProductJpaEntity savedProduct = productJpaRepository.saveAndFlush(jpaEntity);

    Product domainEntity = toDomainEntity(savedProduct);

    return domainEntity;
  }

  @Override
  public Optional<Product> getBySku(String sku) {
    Optional<ProductJpaEntity> jpaEntity = productJpaRepository.findBySku(sku);

    return jpaEntity.map(entity -> toDomainEntity(entity));
  }

  @Override
  public Optional<Product> getById(Long id) {
    Optional<ProductJpaEntity> jpaEntity = productJpaRepository.findById(id);

    return jpaEntity.map(entity -> toDomainEntity(entity));
  }

  @Override
  public Page<Product> getPageableProduct(Pageable pageable) {
    Page<ProductJpaEntity> jpaPage = productJpaRepository.findAll(pageable);

    return jpaPage.map(
        entity -> toDomainEntity(entity));
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
  public boolean existsById(Long id) {
    return productJpaRepository.existsById(id);
  }

  public ProductJpaEntity getReferenceById(Long id) {
    return productJpaRepository.getReferenceById(id);
  }

  private ProductJpaEntity toJpaEntity(Product product) {
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

  private Product toDomainEntity(ProductJpaEntity productJpaEntity) {
    Product domainEntity = new Product();
    domainEntity.setId(productJpaEntity.getId());
    domainEntity.setSku(productJpaEntity.getSku());
    domainEntity.setName(productJpaEntity.getName());
    domainEntity.setSupplierId(productJpaEntity.getSupplier().getId());
    domainEntity.setBaseUnitsOfMeasureId(productJpaEntity.getBaseUnitOfMeasure().getId());
    domainEntity.setCreatedAt(productJpaEntity.getCreatedAt());
    domainEntity.setUpdatedAt(productJpaEntity.getUpdatedAt());

    for (ProductPackagingJpaEntity jpaPackaging : productJpaEntity.getProductPackages()) {
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
