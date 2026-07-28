package com.javv.inventorySystem.infrastructure.persistence.supplier;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;

public interface SupplierJpaRepository extends JpaRepository<SupplierJpaEntity, Integer> {

  @NativeQuery("SELECT supplier_code FROM supplier")
  List<String> findAllSupplierCode();

  boolean existsBySupplierCode(String supplierCode);

  SupplierJpaEntity getRererenceBySupplierCode(String supplierCode);

  Optional<SupplierJpaEntity> findBySupplierCode(String supplierCode);
}
