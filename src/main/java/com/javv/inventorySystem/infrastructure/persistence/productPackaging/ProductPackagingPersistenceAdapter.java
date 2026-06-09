package com.javv.inventorySystem.infrastructure.persistence.productPackaging;

import java.util.ArrayList;
import java.util.List;
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
  public Optional<ProductPackaging> getById(Integer id) {
    Optional<ProductPackagingJpaEntity> optionalEntity = productPackagingJpaRepository.findById(id);

    return optionalEntity.map(
        entity -> toDomainEntity(entity));
  }

  @Override
  public List<ProductPackaging> getByProductId(Long productId) {
    List<ProductPackagingJpaEntity> optionalListEntity = productPackagingJpaRepository
        .findByProductId(productId);

    List<ProductPackaging> listDomain = new ArrayList<ProductPackaging>();
    optionalListEntity.forEach(
        entity -> listDomain.add(
            toDomainEntity(entity)));

    return listDomain;
  }

  public ProductPackagingJpaEntity getReferenceById(Long id) {
    return productPackagingJpaRepository.getReferenceById(id);
  }

  private ProductPackaging toDomainEntity(ProductPackagingJpaEntity productPackagingJpaEntity) {
    ProductPackaging domainEntity = new ProductPackaging();

    domainEntity.setId(productPackagingJpaEntity.getId());
    domainEntity.setPackagingCode(productPackagingJpaEntity.getPackagingCode());
    domainEntity.setProductId(
        productPackagingJpaEntity.getProduct().getId());
    domainEntity.setUnitsOfMeasureId(
        productPackagingJpaEntity.getUnitsOfMeasure().getId());
    domainEntity.setConversionFactor(
        productPackagingJpaEntity.getConversionFactor());
    domainEntity.setPrice(productPackagingJpaEntity.getPrice());

    return domainEntity;
  }
}
