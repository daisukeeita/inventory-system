package com.javv.inventorySystem.domain.repository;

import java.util.List;
import java.util.Optional;

import com.javv.inventorySystem.domain.model.supplier.Supplier;

public interface SupplierRepositoryInterface {
  Supplier save(Supplier supplier);

  Supplier update(Supplier supplier);

  Optional<Supplier> findBySupplierCode(String supplierCode);

  boolean existsBySupplierCode(String supplierCode);

  List<String> findAllSupplierCode();

  Long count();
}
