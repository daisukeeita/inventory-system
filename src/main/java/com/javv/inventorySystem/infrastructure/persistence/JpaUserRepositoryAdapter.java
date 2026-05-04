package com.javv.inventorySystem.infrastructure.persistence;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.javv.inventorySystem.domain.model.user.User;
import com.javv.inventorySystem.domain.repository.user.UserRepositoryInterface;
import com.javv.inventorySystem.infrastructure.persistence.user.UserJpaEntity;

@Repository
public class JpaUserRepositoryAdapter implements UserRepositoryInterface {
  private final SpringDataJpaUserRepository springDataJpaUserRepository;

  public JpaUserRepositoryAdapter(SpringDataJpaUserRepository springDataJpaUserRepository) {
    this.springDataJpaUserRepository = springDataJpaUserRepository;
  }

  @Override
  public Optional<User> findByUsername(String username) {
    Optional<UserJpaEntity> entity = springDataJpaUserRepository.findById(null);

    User user = new User();

    return user;
  }
}
