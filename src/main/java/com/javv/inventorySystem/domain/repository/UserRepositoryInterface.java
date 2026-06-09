package com.javv.inventorySystem.domain.repository;

import java.util.Optional;
import java.util.UUID;

import com.javv.inventorySystem.domain.model.user.User;

public interface UserRepositoryInterface {
  User save(User user);

  Optional<User> findByUsername(String username);

  boolean existsById(UUID id);
}
