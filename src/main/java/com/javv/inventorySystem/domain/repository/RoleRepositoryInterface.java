package com.javv.inventorySystem.domain.repository;

import java.util.Optional;

import com.javv.inventorySystem.domain.model.role.Role;

public interface RoleRepositoryInterface {
  Optional<Role> findById(Integer id);

  Optional<Role> findByName(String name);

  boolean existsByName(String name);

  void save(Role role);
}
