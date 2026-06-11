package com.javv.inventorySystem.infrastructure.persistence.supplier;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javv.inventorySystem.domain.exception.ResourceNotFoundException;
import com.javv.inventorySystem.domain.model.supplier.Supplier;
import com.javv.inventorySystem.domain.repository.SupplierRepositoryInterface;

@Repository
public class SupplierPersistenceAdapter implements SupplierRepositoryInterface {

  @Autowired
  private SupplierJpaRepository supplierJpaRepository;

  @Autowired
  private SupplierPersistenceMapper supplierPersistenceMapper;

  @Override
  public Supplier save(Supplier supplier) {
    SupplierJpaEntity supplierJpaEntity = supplierPersistenceMapper.toJpaEntity(supplier);

    SupplierJpaEntity savedEntity = supplierJpaRepository.saveAndFlush(supplierJpaEntity);

    return supplierPersistenceMapper.toDomainEntity(savedEntity);
  }

  @Override
  public Supplier update(Supplier supplier) {
    SupplierJpaEntity fetchedEntity = supplierJpaRepository
        .findById(supplier.getId())
        .orElseThrow(() -> new ResourceNotFoundException(
            "Supplier Persistence: Supplier not found using the ID: " + supplier.getId() + "."));

    SupplierJpaEntity updatedEntity = supplierPersistenceMapper.updateEntity(supplier, fetchedEntity);

    SupplierJpaEntity savedEntity = supplierJpaRepository.saveAndFlush(updatedEntity);
    return supplierPersistenceMapper.toDomainEntity(savedEntity);
  }

  @Override
  public Optional<Supplier> findByCompanyName(String companyName) {
    Optional<SupplierJpaEntity> jpaEntity = supplierJpaRepository.findByCompanyName(companyName);

    return jpaEntity.map(entity -> supplierPersistenceMapper.toDomainEntity(entity));
  }

  @Override
  public Optional<Supplier> findById(int id) {
    Optional<SupplierJpaEntity> jpaEntity = supplierJpaRepository.findById(id);

    return jpaEntity.map(entity -> supplierPersistenceMapper.toDomainEntity(entity));
  }

  @Override
  public boolean existsById(int id) {
    return supplierJpaRepository.existsById(id);
  }

  public SupplierJpaEntity getReferenceById(Integer id) {
    return supplierJpaRepository.getReferenceById(id);
  }
}
