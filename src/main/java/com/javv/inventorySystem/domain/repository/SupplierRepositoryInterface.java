package com.javv.inventorySystem.domain.repository;

import java.util.Optional;

import com.javv.inventorySystem.domain.model.supplier.Supplier;

public interface SupplierRepositoryInterface {
  Supplier save(Supplier supplier);

  Optional<Supplier> findByName(String companyName);

  Optional<Supplier> findById(int id);
}
