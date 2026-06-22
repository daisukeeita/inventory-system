package com.javv.inventorySystem.infrastructure.persistence.user;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserJpaEntity, UUID> {
  boolean existsByUsername(String username);

  Optional<UserJpaEntity> findByUsername(String username);

  UserJpaEntity getReferenceByUsername(String username);
}
