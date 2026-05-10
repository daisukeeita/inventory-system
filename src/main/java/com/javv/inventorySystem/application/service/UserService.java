package com.javv.inventorySystem.application.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javv.inventorySystem.application.dto.user.UserCreateDto;
import com.javv.inventorySystem.application.mapper.UserRestMapper;
import com.javv.inventorySystem.domain.model.role.Role;
import com.javv.inventorySystem.domain.model.user.User;
import com.javv.inventorySystem.domain.repository.UserRepositoryInterface;
import com.javv.inventorySystem.infrastructure.persistence.user.UserPersistenceMapper;

@Service
@Transactional(readOnly = true)
public class UserService {

  private RoleService roleService;
  private UserRestMapper userRestMapper;
  private BCryptPasswordEncoder passwordEncoder;
  private UserPersistenceMapper userPersistenceMapper;
  private UserRepositoryInterface userRepositoryInterface;

  public UserService(
      RoleService roleService,
      UserRestMapper userRestMapper,
      BCryptPasswordEncoder passwordEncoder,
      UserPersistenceMapper userPersistenceMapper,
      UserRepositoryInterface userRepositoryInterface) {
    this.roleService = roleService;
    this.userRestMapper = userRestMapper;
    this.passwordEncoder = passwordEncoder;
    this.userPersistenceMapper = userPersistenceMapper;
    this.userRepositoryInterface = userRepositoryInterface;
  }

  public User saveUser(UserCreateDto userCreateDto) {
    Role role = roleService.getRoleByName(userCreateDto.getRoleName());

    String hashedPassword = hashPassword(userCreateDto.getPassword());

    User user = userRestMapper.toDomainEntity(userCreateDto, hashedPassword, role);

    return userRepositoryInterface.save(user);
  }

  private String hashPassword(String plainPassword) {
    return passwordEncoder.encode(plainPassword);
  }

  private boolean verifyPassword(String plainPassword, String hashedPassword) {
    return passwordEncoder.matches(plainPassword, hashedPassword);
  }
}
