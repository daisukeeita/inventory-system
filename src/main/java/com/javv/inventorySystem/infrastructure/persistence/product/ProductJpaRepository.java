package com.javv.inventorySystem.infrastructure.persistence.product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.repository.query.Param;

public interface ProductJpaRepository extends JpaRepository<ProductJpaEntity, Long> {

  @NativeQuery("SELECT DISTINCT product.sku from product WHERE product.sku IN :sku")
  List<String> findExistingSku(@Param("sku") List<String> sku);

  boolean existsBySku(String sku);

  Optional<ProductJpaEntity> findBySku(String sku);

  ProductJpaEntity getReferenceBySku(String sku);

  Page<ProductJpaEntity> findAll(Pageable pageable);
}
