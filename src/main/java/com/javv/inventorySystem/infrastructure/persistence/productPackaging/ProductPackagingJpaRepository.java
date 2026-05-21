package com.javv.inventorySystem.infrastructure.persistence.productPackaging;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javv.inventorySystem.domain.model.product.ProductPackaging;

public interface ProductPackagingJpaRepository extends JpaRepository<ProductPackaging, Integer> {

  Optional<ProductPackaging> findByPackagingCode(String packagingCode);
}
