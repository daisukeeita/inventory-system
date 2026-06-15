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
  public Optional<ProductPackaging> findById(Long id) {
    Optional<ProductPackagingJpaEntity> optionalEntity = productPackagingJpaRepository.findById(id);

    return optionalEntity.map(
        entity -> productPackagingPersistenceMapper.toDomainEntity(entity));
  }

  @Override
  public List<ProductPackaging> findByProductId(Long productId) {
    List<ProductPackagingJpaEntity> optionalListEntity = productPackagingJpaRepository
        .findByProductId(productId);

    List<ProductPackaging> listDomain = new ArrayList<ProductPackaging>();
    optionalListEntity.forEach(
        entity -> listDomain.add(
            productPackagingPersistenceMapper.toDomainEntity(entity)));

    return listDomain;
  }

  @Override
  public List<ProductPackaging> findAllById(List<Long> id) {
    List<ProductPackagingJpaEntity> listJpa = productPackagingJpaRepository.findAllById(id);

    return listJpa.stream()
        .map(jpaEntity -> productPackagingPersistenceMapper
            .toDomainEntity(jpaEntity))
        .toList();
  }

  public ProductPackagingJpaEntity getReferenceById(Long id) {
    return productPackagingJpaRepository.getReferenceById(id);
  }
}
