package com.javv.inventorySystem.infrastructure.persistence.productPackaging;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductPackagingJpaRepository
    extends JpaRepository<ProductPackagingJpaEntity, Long> {

  List<ProductPackagingJpaEntity> findByProductId(Long id);
}
