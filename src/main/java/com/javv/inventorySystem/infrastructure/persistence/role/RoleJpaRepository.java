package com.javv.inventorySystem.infrastructure.persistence.role;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface RoleJpaRepository extends CrudRepository<RoleJpaEntity, Integer> {
  Optional<RoleJpaEntity> findByName(String name);

  boolean existsByName(String name);
}
