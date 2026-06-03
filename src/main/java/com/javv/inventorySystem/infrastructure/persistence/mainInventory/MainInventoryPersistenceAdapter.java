package com.javv.inventorySystem.infrastructure.persistence.mainInventory;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.javv.inventorySystem.domain.exception.ResourceNotFoundException;
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

    return mainInventoryPersistenceMapper.toDomainEntity(savedEntity);

  }

  @Override
  public MainInventory update(MainInventory mainInventory) {
    MainInventoryJpaEntity fetchedEntity = mainInventoryJpaRepository.findByProductJpaEntitySku(
        mainInventory.getProductSku()).orElseThrow(
            () -> new ResourceNotFoundException(
                "Main Inventory Persistence Adapter: Product Inventory not found."));

    MainInventoryJpaEntity updatedEntity = mainInventoryPersistenceMapper
        .updateJpaEntity(mainInventory, fetchedEntity);

    MainInventoryJpaEntity savedEntity = mainInventoryJpaRepository.saveAndFlush(updatedEntity);

    return mainInventoryPersistenceMapper.toDomainEntity(savedEntity);
  }

  @Override
  public Optional<MainInventory> getById(Integer id) {
    Optional<MainInventoryJpaEntity> fetchedEntity = mainInventoryJpaRepository.findById(id);

    return fetchedEntity.map(entity -> mainInventoryPersistenceMapper.toDomainEntity(entity));
  }

  @Override
  public Optional<MainInventory> getBySku(String sku) {
    Optional<MainInventoryJpaEntity> fetchedEntity = mainInventoryJpaRepository.findByProductJpaEntitySku(sku);

    return fetchedEntity.map(entity -> mainInventoryPersistenceMapper.toDomainEntity(entity));
  }
}
