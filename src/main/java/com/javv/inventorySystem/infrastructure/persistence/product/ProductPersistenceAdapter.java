package com.javv.inventorySystem.infrastructure.persistence.product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.javv.inventorySystem.domain.model.product.Product;
import com.javv.inventorySystem.domain.repository.ProductRepositoryInterface;
import com.javv.inventorySystem.infrastructure.persistence.supplier.SupplierJpaRepository;
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
    ProductJpaEntity jpaEntity = productPersistenceMapper.toJpaEntity(product);

    ProductJpaEntity savedProduct = productJpaRepository.saveAndFlush(jpaEntity);

    Product domainEntity = productPersistenceMapper.toDomainEntity(savedProduct);

    return domainEntity;
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

  @Override
  public Page<Product> getPageableProduct(Pageable pageable) {
    Page<ProductJpaEntity> jpaPage = productJpaRepository.findAll(pageable);

    return jpaPage.map(
        entity -> productPersistenceMapper.toDomainEntity(entity));
  }

  @Override
  public Product update(Product product) {
    ProductJpaEntity productJpaEntity = productPersistenceMapper
        .toJpaEntity(product);

    ProductJpaEntity savedProduct = productJpaRepository
        .saveAndFlush(productJpaEntity);

    return productPersistenceMapper.toDomainEntity(savedProduct);
  }

  @Override
  public boolean existsById(Long id) {
    return productJpaRepository.existsById(id);
  }

  @Override
  public List<Product> getAllById(List<Long> id) {
    List<ProductJpaEntity> listJpa = productJpaRepository.findAllById(id);

    return listJpa.stream()
        .map(jpaEntity -> productPersistenceMapper.toDomainEntity(
            jpaEntity))
        .toList();
  }

  public ProductJpaEntity getReferenceById(Long id) {
    return productJpaRepository.getReferenceById(id);
  }
}
