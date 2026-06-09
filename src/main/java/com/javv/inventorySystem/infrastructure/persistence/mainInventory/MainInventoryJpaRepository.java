package com.javv.inventorySystem.infrastructure.persistence.mainInventory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MainInventoryJpaRepository extends JpaRepository<MainInventoryJpaEntity, Integer> {

  Optional<MainInventoryJpaEntity> findByProductJpaEntityId(Long id);
}
