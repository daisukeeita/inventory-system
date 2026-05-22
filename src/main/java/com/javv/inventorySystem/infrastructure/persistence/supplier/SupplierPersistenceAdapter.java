package com.javv.inventorySystem.infrastructure.persistence.supplier;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.javv.inventorySystem.domain.exception.ResourceNotFoundException;
import com.javv.inventorySystem.domain.model.supplier.Supplier;
import com.javv.inventorySystem.domain.repository.SupplierRepositoryInterface;

@Repository
public class SupplierPersistenceAdapter implements SupplierRepositoryInterface {
  private final SupplierJpaRepository supplierJpaRepository;
  private final SupplierPersistenceMapper supplierPersistenceMapper;

  public SupplierPersistenceAdapter(
      SupplierJpaRepository supplierJpaRepository,
      SupplierPersistenceMapper supplierPersistenceMapper) {
    this.supplierJpaRepository = supplierJpaRepository;
    this.supplierPersistenceMapper = supplierPersistenceMapper;
  }

  @Override
  public Supplier save(Supplier supplier) {
    SupplierJpaEntity supplierJpaEntity = supplierPersistenceMapper.toJpaEntity(supplier);

    SupplierJpaEntity savedEntity = supplierJpaRepository.save(supplierJpaEntity);

    return supplierPersistenceMapper.toDomainEntity(savedEntity);
  }

  @Override
  public Supplier update(Supplier supplier) {
    SupplierJpaEntity fetchedEntity =
        supplierJpaRepository
            .findById(supplier.getId())
            .orElseThrow(() -> new ResourceNotFoundException("Entity not found."));

    SupplierJpaEntity updatedEntity =
        supplierPersistenceMapper.updateEntity(supplier, fetchedEntity);

    SupplierJpaEntity savedEntity = supplierJpaRepository.saveAndFlush(updatedEntity);
    return supplierPersistenceMapper.toDomainEntity(savedEntity);
  }

  @Override
  public Optional<Supplier> findByName(String companyName) {
    Optional<SupplierJpaEntity> jpaEntity = supplierJpaRepository.findByCompanyName(companyName);

    return jpaEntity.map(entity -> supplierPersistenceMapper.toDomainEntity(entity));
  }

  @Override
  public Optional<Supplier> findById(Integer id) {
    Optional<SupplierJpaEntity> jpaEntity = supplierJpaRepository.findById(id);

    return jpaEntity.map(entity -> supplierPersistenceMapper.toDomainEntity(entity));
  }
}
