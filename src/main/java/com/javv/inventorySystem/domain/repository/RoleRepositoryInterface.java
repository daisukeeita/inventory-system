package com.javv.inventorySystem.domain.repository;

import java.util.Optional;

import com.javv.inventorySystem.domain.model.role.Role;

public interface RoleRepositoryInterface {
  public Optional<Role> findById(Integer id);
}
