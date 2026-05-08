package com.javv.inventorySystem.application.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javv.inventorySystem.domain.exception.EntityAlreadyExistsException;
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
    Role role = optionalRole.orElseThrow(
        () -> new ResourceNotFoundException("Role not found in database using the id: " + id));
    return role;
  }

  public Role getRoleByName(String name) {
    Optional<Role> optionalRole = roleRepositoryInterface.findByName(name.trim().toUpperCase());
    Role role = optionalRole.orElseThrow(
        () -> new ResourceNotFoundException("Role not found in database using the name: " + name));
    return role;
  }

  @Transactional
  public void saveRole(Role role) {
    if (roleRepositoryInterface.existsByName(role.getName())) {
      throw new EntityAlreadyExistsException("Role Name already in use!");
    }
    roleRepositoryInterface.save(role);
  }
}
