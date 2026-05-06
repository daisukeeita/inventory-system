package com.javv.inventorySystem.domain.repository.user;

import java.util.Optional;

import com.javv.inventorySystem.domain.model.user.User;

public interface UserRepositoryInterface {
  void save(User user);

  Optional<User> findByUsername(String username);
}
