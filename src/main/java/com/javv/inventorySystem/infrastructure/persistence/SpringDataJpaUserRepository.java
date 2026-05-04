package com.javv.inventorySystem.infrastructure.persistence;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javv.inventorySystem.infrastructure.persistence.user.UserJpaEntity;

interface SpringDataJpaUserRepository extends JpaRepository<UserJpaEntity, UUID> {
}
