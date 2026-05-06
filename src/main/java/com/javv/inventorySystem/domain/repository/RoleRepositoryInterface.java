package com.javv.inventorySystem.domain.repository;

import java.util.Optional;

import com.javv.inventorySystem.domain.model.role.Role;

public interface RoleRepositoryInterface {
  Optional<Role> findById(Integer id);

  void save(Role role);
}
