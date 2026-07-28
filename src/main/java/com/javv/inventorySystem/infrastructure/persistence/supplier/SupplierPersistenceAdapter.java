package com.javv.inventorySystem.infrastructure.persistence.supplier;

import java.util.List;
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

    Supplier supplierDomain = supplierPersistenceMapper.toDomainEntity(savedEntity);

    return supplierDomain;
  }

  @Override
  public Supplier update(Supplier supplier) {
    String supplierCode = supplier.getSupplierCode();

    SupplierJpaEntity fetchedEntity = supplierJpaRepository
        .findBySupplierCode(supplierCode)
        .orElseThrow(() -> new ResourceNotFoundException(
            "Supplier Persistence: Supplier not found with supplier code: " + supplierCode + "."));

    SupplierJpaEntity updatedEntity = supplierPersistenceMapper.updateEntity(supplier, fetchedEntity);

    SupplierJpaEntity savedEntity = supplierJpaRepository.saveAndFlush(updatedEntity);

    Supplier supplierDomain = supplierPersistenceMapper.toDomainEntity(savedEntity);

    return supplierDomain;
  }

  @Override
  public Optional<Supplier> findBySupplierCode(String supplierCode) {
    Optional<SupplierJpaEntity> jpaEntity = supplierJpaRepository.findBySupplierCode(supplierCode);

    Optional<Supplier> domainEntity = jpaEntity
        .map(entity -> supplierPersistenceMapper.toDomainEntity(entity));

    return domainEntity;
  }

  @Override
  public boolean existsBySupplierCode(String supplierCode) {
    boolean result = supplierJpaRepository.existsBySupplierCode(supplierCode);

    return result;
  }

  @Override
  public List<String> findAllSupplierCode() {
    List<String> listOfSupplierCode = supplierJpaRepository.findAllSupplierCode();

    return listOfSupplierCode;
  }

  @Override
  public Long count() {
    Long result = supplierJpaRepository.count();

    return result;
  }
}
