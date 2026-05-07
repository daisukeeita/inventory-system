package com.javv.inventorySystem.application.service;

import java.util.Optional;

import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javv.inventorySystem.domain.exception.ResourceNotFoundException;
import com.javv.inventorySystem.domain.exception.RoleAlreadyExistsException;
import com.javv.inventorySystem.domain.exception.SystemUnavailableException;
import com.javv.inventorySystem.domain.model.role.Role;
import com.javv.inventorySystem.infrastructure.persistence.role.RolePersistenceAdapter;

@Service
@Transactional(readOnly = true)
public class RoleService {
  private RolePersistenceAdapter rolePersistenceAdapter;

  public RoleService(RolePersistenceAdapter rolePersistenceAdapter) {
    this.rolePersistenceAdapter = rolePersistenceAdapter;
  }

  public Role getRoleById(int id) {
    Optional<Role> optionalRole = rolePersistenceAdapter.findById(id);
    Role role =
        optionalRole.orElseThrow(
            () -> new ResourceNotFoundException("Role not found in database using the id: " + id));
    return role;
  }

  @Transactional
  public void saveRole(Role role) {
    try {
      rolePersistenceAdapter.save(role);
    } catch (DataIntegrityViolationException exception) {
      throw new RoleAlreadyExistsException("Role Name already in use", exception);
    } catch (DataAccessResourceFailureException exception) {
      throw new SystemUnavailableException("Persistence service is down", exception);
    }
  }
}
