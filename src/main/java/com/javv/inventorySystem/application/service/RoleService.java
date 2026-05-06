package com.javv.inventorySystem.application.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javv.inventorySystem.domain.model.role.Role;
import com.javv.inventorySystem.infrastructure.persistence.role.RolePersistenceAdapter;

@Service
@Transactional
public class RoleService {
  private RolePersistenceAdapter rolePersistenceAdapter;

  public RoleService(RolePersistenceAdapter rolePersistenceAdapter) {
    this.rolePersistenceAdapter = rolePersistenceAdapter;
  }

  public Role getRoleById(int id) {
    Optional<Role> optionalRole = rolePersistenceAdapter.findById(id);
    Role role = optionalRole.orElseThrow();
    return role;
  }

  public void saveRole(Role role) {
    rolePersistenceAdapter.save(role);
  }
}
