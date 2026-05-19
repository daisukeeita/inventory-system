package com.javv.inventorySystem.infrastructure.persistence.product;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.javv.inventorySystem.domain.model.product.Product;
import com.javv.inventorySystem.domain.repository.ProductRepositoryInterface;

@Repository
public class ProductPersistenceAdapter implements ProductRepositoryInterface {
  private final ProductJpaRepository productJpaRepository;
  private final ProductPersistenceMapper productPersistenceMapper;

  public ProductPersistenceAdapter(
      ProductJpaRepository productJpaRepository,
      ProductPersistenceMapper productPersistenceMapper) {
    this.productJpaRepository = productJpaRepository;
    this.productPersistenceMapper = productPersistenceMapper;
  }

  @Override
  public Product save(Product product) {

    ProductJpaEntity productJpaEntity = productPersistenceMapper.toJpaEntity(product);

    ProductJpaEntity savedProduct = productJpaRepository.saveAndFlush(productJpaEntity);

    return productPersistenceMapper.toDomainEntity(savedProduct);

  }

  @Override
  public Optional<Product> getBySku(String sku) {

    Optional<ProductJpaEntity> jpaEntity = productJpaRepository.findById(sku);

    return jpaEntity.map(entity -> productPersistenceMapper.toDomainEntity(entity));
  }
}
