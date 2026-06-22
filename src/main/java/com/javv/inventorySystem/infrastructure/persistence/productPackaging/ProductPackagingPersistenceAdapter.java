package com.javv.inventorySystem.infrastructure.persistence.productPackaging;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javv.inventorySystem.domain.model.product.ProductPackaging;
import com.javv.inventorySystem.domain.repository.ProductPackagingRepositoryInterface;

@Repository
public class ProductPackagingPersistenceAdapter implements ProductPackagingRepositoryInterface {

  @Autowired
  private ProductPackagingJpaRepository productPackagingJpaRepository;

  @Autowired
  private ProductPackagingPersistenceMapper productPackagingPersistenceMapper;

  @Override
  public Optional<ProductPackaging> findByPackagingCode(String packagingCode) {
    Optional<ProductPackagingJpaEntity> optionalEntity = productPackagingJpaRepository
        .findByPackagingCode(packagingCode);

    return optionalEntity.map(
        entity -> productPackagingPersistenceMapper.toDomainEntity(entity));
  }

  @Override
  public List<ProductPackaging> findByProductSku(String productSku) {
    List<ProductPackagingJpaEntity> optionalListEntity = productPackagingJpaRepository
        .findByProductSku(productSku);

    List<ProductPackaging> listDomain = new ArrayList<ProductPackaging>();

    optionalListEntity.forEach(
        entity -> listDomain.add(
            productPackagingPersistenceMapper.toDomainEntity(entity)));

    return listDomain;
  }

  @Override
  public List<ProductPackaging> findAllByPackagingCode(List<String> packagingCode) {
    List<ProductPackagingJpaEntity> listEntity = productPackagingJpaRepository
        .findAllByPackagingCodeIn(packagingCode);

    return listEntity.stream()
        .map(entity -> productPackagingPersistenceMapper.toDomainEntity(entity))
        .toList();
  }

  @Override
  public List<String> findExistingPackagingCodes(List<String> packagingCodes) {
    return productPackagingJpaRepository.findExistingPackagingCode(packagingCodes);
  }

  @Override
  public boolean existsByPackagingCode(String packagingCode) {
    return productPackagingJpaRepository.existsByPackagingCode(packagingCode);
  }
}
