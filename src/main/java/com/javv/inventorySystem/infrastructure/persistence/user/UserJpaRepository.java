package com.javv.inventorySystem.infrastructure.persistence.user;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserJpaEntity, UUID> {

  UserJpaEntity save(UserJpaEntity userJpaEntity);

  Optional<UserJpaEntity> findByUsername(String username);
}
