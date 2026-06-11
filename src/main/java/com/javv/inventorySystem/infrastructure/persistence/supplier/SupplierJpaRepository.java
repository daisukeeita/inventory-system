package com.javv.inventorySystem.infrastructure.persistence.supplier;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierJpaRepository extends JpaRepository<SupplierJpaEntity, Integer> {
  Optional<SupplierJpaEntity> findByCompanyName(String companyName);

  Optional<SupplierJpaEntity> findByEmail(String email);

  boolean existsById(int id);

  boolean existsByEmail(String email);

}
