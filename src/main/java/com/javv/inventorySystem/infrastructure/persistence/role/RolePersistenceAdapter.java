package com.javv.inventorySystem.infrastructure.persistence.role;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.javv.inventorySystem.domain.model.role.Role;
import com.javv.inventorySystem.domain.repository.RoleRepositoryInterface;

@Repository
public class RolePersistenceAdapter implements RoleRepositoryInterface {
  private RoleJpaRepository roleJpaRepository;
  private RolePersistenceMapper rolePersistenceMapper;

  public RolePersistenceAdapter(RoleJpaRepository roleJpaRepository, RolePersistenceMapper rolePersistenceMapper) {
    this.roleJpaRepository = roleJpaRepository;
    this.rolePersistenceMapper = rolePersistenceMapper;
  }

  @Override
  public Optional<Role> findById(Integer id) {
    Optional<RoleJpaEntity> roleEntity = roleJpaRepository.findById(id);
    Optional<Role> role = roleEntity.map(entity -> rolePersistenceMapper.entityToDomain(entity));
    return null;
  }
}
