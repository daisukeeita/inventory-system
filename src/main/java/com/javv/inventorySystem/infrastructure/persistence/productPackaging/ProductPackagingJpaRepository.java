package com.javv.inventorySystem.infrastructure.persistence.productPackaging;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductPackagingJpaRepository
    extends JpaRepository<ProductPackagingJpaEntity, Integer> {

  Optional<ProductPackagingJpaEntity> findByPackagingCode(String packagingCode);
}
