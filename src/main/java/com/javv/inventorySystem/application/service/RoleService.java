package com.javv.inventorySystem.application.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javv.inventorySystem.domain.exception.ResourceNotFoundException;
import com.javv.inventorySystem.domain.model.role.Role;
import com.javv.inventorySystem.domain.repository.RoleRepositoryInterface;

@Service
@Transactional(readOnly = true)
public class RoleService {
  private RoleRepositoryInterface roleRepositoryInterface;

  public RoleService(RoleRepositoryInterface roleRepositoryInterface) {
    this.roleRepositoryInterface = roleRepositoryInterface;
  }

  public Role getRoleById(int id) {
    Optional<Role> optionalRole = roleRepositoryInterface.findById(id);
    Role role =
        optionalRole.orElseThrow(
            () -> new ResourceNotFoundException("Role not found in database using the id: " + id));
    return role;
  }

  @Transactional
  public void saveRole(Role role) {
    roleRepositoryInterface.save(role);
  }
}
