package com.javv.inventorySystem.domain.repository;

import java.util.Optional;

import com.javv.inventorySystem.domain.model.user.User;

public interface UserRepositoryInterface {
  User save(User user);

  Optional<User> findByUsername(String username);
}
