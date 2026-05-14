package com.javv.inventorySystem.application.service.supplier;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javv.inventorySystem.domain.repository.SupplierRepositoryInterface;

@Service
@Transactional(readOnly = true)
public class SupplierService {

  private SupplierRepositoryInterface supplierRepositoryInterface;

  public SupplierService(SupplierRepositoryInterface supplierRepositoryInterface) {
    this.supplierRepositoryInterface = supplierRepositoryInterface;
  }
}
