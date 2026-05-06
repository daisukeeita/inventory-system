package com.javv.inventorySystem.infrastructure.persistence.role;

import org.springframework.stereotype.Component;

import com.javv.inventorySystem.domain.model.role.Role;

@Component
public class RolePersistenceMapper {

  public Role entityToDomain(RoleJpaEntity roleJpaEntity) {
    Role role = new Role();
    role.setId(roleJpaEntity.getId());
    role.setName(roleJpaEntity.getName());
    return role;
  }

  public RoleJpaEntity domainToEntity(Role role) {
    RoleJpaEntity roleJpaEntity = new RoleJpaEntity();
    roleJpaEntity.setId(role.getId());
    roleJpaEntity.setName(role.getName());
    return roleJpaEntity;
  }
}
