package com.javv.inventorySystem.infrastructure.persistence.product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.javv.inventorySystem.domain.model.product.Product;
import com.javv.inventorySystem.domain.repository.ProductRepositoryInterface;

@Repository
public class ProductPersistenceAdapter implements ProductRepositoryInterface {

  @Autowired
  private ProductJpaRepository productJpaRepository;

  @Autowired
  private ProductPersistenceMapper productPersistenceMapper;

  @Override
  public Product save(Product product) {

    ProductJpaEntity jpaEntity = productPersistenceMapper.toJpaEntity(product);

    ProductJpaEntity savedProduct = productJpaRepository.saveAndFlush(jpaEntity);

    Product domainEntity = productPersistenceMapper.toDomainEntity(savedProduct);

    return domainEntity;
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
  public Optional<Product> findBySku(String sku) {
    Optional<ProductJpaEntity> jpaEntity = productJpaRepository.findBySku(sku);

    return jpaEntity.map(entity -> productPersistenceMapper.toDomainEntity(entity));
  }

  @Override
  public Optional<Product> findById(Long id) {
    Optional<ProductJpaEntity> jpaEntity = productJpaRepository.findById(id);

    return jpaEntity.map(entity -> productPersistenceMapper.toDomainEntity(entity));
  }

  @Override
  public List<Product> findAllById(List<Long> id) {
    List<ProductJpaEntity> listJpa = productJpaRepository.findAllById(id);

    return listJpa.stream()
        .map(jpaEntity -> productPersistenceMapper.toDomainEntity(jpaEntity))
        .toList();
  }

  @Override
  public Page<Product> findAll(Pageable pageable) {
    Page<ProductJpaEntity> jpaPage = productJpaRepository.findAll(pageable);

    return jpaPage.map(
        entity -> productPersistenceMapper.toDomainEntity(entity));
  }

  @Override
  public boolean existsById(Long id) {
    return productJpaRepository.existsById(id);
  }

  @Override
  public Long countByIdIn(List<Long> listId) {
    return productJpaRepository.countByIdIn(listId);
  }

  public ProductJpaEntity getReferenceById(Long id) {
    return productJpaRepository.getReferenceById(id);
  }
}
