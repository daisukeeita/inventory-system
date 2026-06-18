package com.javv.inventorySystem.infrastructure.persistence.supplier;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierJpaRepository extends JpaRepository<SupplierJpaEntity, Integer> {
  boolean existsBySupplierCode(String supplierCode);

  SupplierJpaEntity getRererenceBySupplierCode(String supplierCode);

  Optional<SupplierJpaEntity> findBySupplierCode(String supplierCode);
}
