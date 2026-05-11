package com.javv.inventorySystem.application.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javv.inventorySystem.application.dto.user.UserCreateDto;
import com.javv.inventorySystem.application.dto.user.UserResponseDto;
import com.javv.inventorySystem.application.mapper.UserRestMapper;
import com.javv.inventorySystem.domain.model.role.Role;
import com.javv.inventorySystem.domain.model.user.User;
import com.javv.inventorySystem.domain.repository.UserRepositoryInterface;

@Service
@Transactional(readOnly = true)
public class UserService {

  private RoleService roleService;
  private UserRestMapper userRestMapper;
  private BCryptPasswordEncoder passwordEncoder;
  private UserRepositoryInterface userRepositoryInterface;

  public UserService(
      RoleService roleService,
      UserRestMapper userRestMapper,
      BCryptPasswordEncoder passwordEncoder,
      UserRepositoryInterface userRepositoryInterface) {
    this.roleService = roleService;
    this.userRestMapper = userRestMapper;
    this.passwordEncoder = passwordEncoder;
    this.userRepositoryInterface = userRepositoryInterface;
  }

  @Transactional
  public UserResponseDto saveUser(UserCreateDto userCreateDto) {
    Role role = roleService.getRoleByName(userCreateDto.getRoleName());

    String hashedPassword = hashPassword(userCreateDto.getPassword());

    User user = userRestMapper.toDomainEntity(userCreateDto, hashedPassword, role);

    User savedUser = userRepositoryInterface.save(user);

    return userRestMapper.toDtoEntity(savedUser);
  }

  private String hashPassword(String plainPassword) {
    return passwordEncoder.encode(plainPassword);
  }

  private boolean verifyPassword(String plainPassword, String hashedPassword) {
    return passwordEncoder.matches(plainPassword, hashedPassword);
  }
}
