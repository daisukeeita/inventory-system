package com.javv.inventorySystem.infrastructure.persistence.mainInventory;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.javv.inventorySystem.domain.exception.ResourceNotFoundException;
import com.javv.inventorySystem.domain.model.mainInventory.MainInventory;
import com.javv.inventorySystem.domain.repository.MainInventoryRepositoryInterface;
import com.javv.inventorySystem.infrastructure.persistence.product.ProductJpaEntity;
import com.javv.inventorySystem.infrastructure.persistence.product.ProductJpaRepository;

@Repository
public class MainInventoryPersistenceAdapter implements MainInventoryRepositoryInterface {

  private ProductJpaRepository productJpaRepository;
  private MainInventoryJpaRepository mainInventoryJpaRepository;
  private MainInventoryPersistenceMapper mainInventoryPersistenceMapper;

  public MainInventoryPersistenceAdapter(
      ProductJpaRepository productJpaRepository,
      MainInventoryJpaRepository mainInventoryJpaRepository,
      MainInventoryPersistenceMapper mainInventoryPersistenceMapper) {
    this.productJpaRepository = productJpaRepository;
    this.mainInventoryJpaRepository = mainInventoryJpaRepository;
    this.mainInventoryPersistenceMapper = mainInventoryPersistenceMapper;
  }

  @Override
  public MainInventory save(MainInventory mainInventory) {
    ProductJpaEntity productJpaEntity = productJpaRepository
        .getReferenceBySku(mainInventory.getProductSku());

    MainInventoryJpaEntity jpaEntity = mainInventoryPersistenceMapper
        .toJpaEntity(mainInventory, productJpaEntity);

    MainInventoryJpaEntity savedEntity = mainInventoryJpaRepository.saveAndFlush(jpaEntity);

    return mainInventoryPersistenceMapper.toDomainEntity(savedEntity);

  }

  @Override
  public MainInventory update(MainInventory mainInventory) {
    MainInventoryJpaEntity fetchedEntity = mainInventoryJpaRepository.findById(
        mainInventory.getId()).orElseThrow(
            () -> new ResourceNotFoundException(
                "Main Inventory Persistence: Inventory not found using its ID: " + mainInventory.getId() + "."));

    MainInventoryJpaEntity updatedEntity = mainInventoryPersistenceMapper
        .updateJpaEntity(mainInventory, fetchedEntity);

    MainInventoryJpaEntity savedEntity = mainInventoryJpaRepository.saveAndFlush(updatedEntity);

    return mainInventoryPersistenceMapper.toDomainEntity(savedEntity);
  }

  @Override
  public Optional<MainInventory> findById(int id) {
    Optional<MainInventoryJpaEntity> fetchedEntity = mainInventoryJpaRepository.findById(id);

    return fetchedEntity.map(entity -> mainInventoryPersistenceMapper.toDomainEntity(entity));
  }

  @Override
  public Optional<MainInventory> findByProductSku(String productSku) {
    Optional<MainInventoryJpaEntity> fetchedEntity = mainInventoryJpaRepository.findByProductSku(productSku);

    return fetchedEntity.map(entity -> mainInventoryPersistenceMapper.toDomainEntity(entity));
  }

  @Override
  public Page<MainInventory> findAll(Pageable pageable) {

    Page<MainInventoryJpaEntity> jpaPage = mainInventoryJpaRepository.findAll(pageable);

    return jpaPage.map(
        entity -> mainInventoryPersistenceMapper.toDomainEntity(
            entity));

  }
}
