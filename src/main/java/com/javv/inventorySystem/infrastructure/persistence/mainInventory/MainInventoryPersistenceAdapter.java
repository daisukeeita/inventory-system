package com.javv.inventorySystem.infrastructure.persistence.mainInventory;

import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import com.javv.inventorySystem.domain.exception.EntityAlreadyExistsException;
import com.javv.inventorySystem.domain.model.mainInventory.MainInventory;
import com.javv.inventorySystem.domain.repository.MainInventoryRepositoryInterface;

@Repository
public class MainInventoryPersistenceAdapter implements MainInventoryRepositoryInterface {

  private MainInventoryJpaRepository mainInventoryJpaRepository;
  private MainInventoryPersistenceMapper mainInventoryPersistenceMapper;

  public MainInventoryPersistenceAdapter(
      MainInventoryJpaRepository mainInventoryJpaRepository,
      MainInventoryPersistenceMapper mainInventoryPersistenceMapper) {
    this.mainInventoryJpaRepository = mainInventoryJpaRepository;
    this.mainInventoryPersistenceMapper = mainInventoryPersistenceMapper;
  }

  @Override
  public MainInventory save(MainInventory mainInventory) {

    MainInventoryJpaEntity jpaEntity = mainInventoryPersistenceMapper.toJpaEntity(mainInventory);

    MainInventoryJpaEntity savedEntity = mainInventoryJpaRepository.save(jpaEntity);

    try {
      return mainInventoryPersistenceMapper.toDomainEntity(savedEntity);
    } catch (DataIntegrityViolationException exception) {

      throw new EntityAlreadyExistsException(
          "Main Inventory Persistence Adapter: Inventory for this product already exists.");
    }
  }

  @Override
  public MainInventory update(MainInventory mainInventory) {
    return null;
  }

  @Override
  public Optional<MainInventory> getById(Integer id) {
    return null;
  }

  @Override
  public Optional<MainInventory> getBySku(String sku) {
    return null;
  }
}
