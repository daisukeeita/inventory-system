package com.javv.inventorySystem.infrastructure.persistence;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.javv.inventorySystem.application.mapper.UserMapper;
import com.javv.inventorySystem.domain.model.user.User;
import com.javv.inventorySystem.domain.repository.user.UserRepositoryInterface;
import com.javv.inventorySystem.infrastructure.persistence.user.UserJpaEntity;

/**
 * This is where the system "translates" the JPA Entity (User) to Domain Entity (User) after getting
 * the data from the database.
 *
 * <p>This is where the system "translates" the Domain Entity (User) to JPA Entity (User) before
 * inserting the object to the database.
 */
@Repository
public class JpaUserRepositoryAdapter implements UserRepositoryInterface {
  private final SpringDataJpaUserRepository springDataJpaUserRepository;
  private final UserMapper userMapper;

  public JpaUserRepositoryAdapter(
      SpringDataJpaUserRepository springDataJpaUserRepository, UserMapper userMapper) {
    this.springDataJpaUserRepository = springDataJpaUserRepository;
    this.userMapper = userMapper;
  }

  @Override
  public Optional<User> findByUsername(String username) {
    Optional<UserJpaEntity> entity = springDataJpaUserRepository.findById(null);

    User user = new User();

    return null;
  }

  @Override
  public void save(User user) {}
}
