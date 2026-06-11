package com.javv.inventorySystem.domain.repository;

import java.util.Optional;

import com.javv.inventorySystem.domain.model.supplier.Supplier;

public interface SupplierRepositoryInterface {
  Supplier save(Supplier supplier);

  Supplier update(Supplier supplier);

  Optional<Supplier> findById(int id);

  Optional<Supplier> findByCompanyName(String companyName);

  Optional<Supplier> findByEmail(String email);

  boolean existsById(int id);

  boolean existsByEmail(String email);
}
