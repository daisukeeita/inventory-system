package com.javv.inventorySystem.infrastructure.persistence.productPackaging;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.repository.query.Param;

public interface ProductPackagingJpaRepository
    extends JpaRepository<ProductPackagingJpaEntity, Long> {

  @NativeQuery("SELECT DISTINCT product_packaging.packaging_code FROM product_packaging WHERE product_packaging.product_packaging IN :packaging_code")
  List<String> findExistingPackagingCode(@Param("packaging_code") List<String> packagingCode);

  List<ProductPackagingJpaEntity> findAllByPackagingCodeIn(List<String> packagingCodes);

  List<ProductPackagingJpaEntity> findByProductSku(String productSku);

  Optional<ProductPackagingJpaEntity> findByPackagingCode(String packagingCode);

  ProductPackagingJpaEntity getReferenceByPackagingCode(String packagingCode);

  boolean existsByPackagingCode(String packagingCode);
}
