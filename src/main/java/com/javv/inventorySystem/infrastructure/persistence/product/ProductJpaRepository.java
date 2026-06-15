package com.javv.inventorySystem.infrastructure.persistence.product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<ProductJpaEntity, Long> {

  Optional<ProductJpaEntity> findBySku(String sku);

  Long countByIdIn(List<Long> listId);
}
