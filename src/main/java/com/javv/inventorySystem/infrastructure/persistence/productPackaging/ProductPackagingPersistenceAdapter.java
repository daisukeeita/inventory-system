package com.javv.inventorySystem.infrastructure.persistence.productPackaging;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.javv.inventorySystem.domain.model.product.ProductPackaging;
import com.javv.inventorySystem.domain.repository.ProductPackagingRepositoryInterface;

@Repository
public class ProductPackagingPersistenceAdapter implements ProductPackagingRepositoryInterface {

  private ProductPackagingJpaRepository productPackagingJpaRepository;
  private ProductPackagingPersistenceMapper productPackagingPersistenceMapper;

  public ProductPackagingPersistenceAdapter(
      ProductPackagingJpaRepository productPackagingJpaRepository,
      ProductPackagingPersistenceMapper productPackagingPersistenceMapper) {
    this.productPackagingJpaRepository = productPackagingJpaRepository;
    this.productPackagingPersistenceMapper = productPackagingPersistenceMapper;
  }

  @Override
  public ProductPackaging save(ProductPackaging productPackaging) {

    ProductPackagingJpaEntity productPackagingJpaEntity =
        productPackagingPersistenceMapper.toJpaEntity(productPackaging);

    ProductPackagingJpaEntity savedEntity =
        productPackagingJpaRepository.save(productPackagingJpaEntity);

    return productPackagingPersistenceMapper.toDomainEntity(savedEntity);
  }

  @Override
  public ProductPackaging update(ProductPackaging productPackaging) {
    return null;
  }

  @Override
  public Optional<ProductPackaging> getByPackagingCode(String packagingCode) {
    Optional<ProductPackagingJpaEntity> optionalEntity =
        productPackagingJpaRepository.findByPackagingCode(packagingCode);

    return optionalEntity.map(entity -> productPackagingPersistenceMapper.toDomainEntity(entity));
  }

  @Override
  public Optional<ProductPackaging> getById(Integer id) {
    Optional<ProductPackagingJpaEntity> optionalEntity = productPackagingJpaRepository.findById(id);

    return optionalEntity.map(entity -> productPackagingPersistenceMapper.toDomainEntity(entity));
  }
}
