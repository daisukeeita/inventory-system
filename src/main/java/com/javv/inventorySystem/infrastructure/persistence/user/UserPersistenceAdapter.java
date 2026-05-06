package com.javv.inventorySystem.infrastructure.persistence;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.javv.inventorySystem.domain.model.user.User;
import com.javv.inventorySystem.domain.repository.user.UserRepositoryInterface;
import com.javv.inventorySystem.infrastructure.persistence.mapper.UserJpaMapper;

/**
 * This is where the system "translates" the JPA Entity (User) to Domain Entity
 * (User) after getting
 * the data from the database.
 *
 * <p>
 * This is where the system "translates" the Domain Entity (User) to JPA Entity
 * (User) before
 * inserting the object to the database.
 */
@Repository
public class JpaUserRepositoryAdapter implements UserRepositoryInterface {
  private final SpringJpaUserRepository springDataJpaUserRepository;
  private final UserJpaMapper userMapper;

  public JpaUserRepositoryAdapter(
      SpringJpaUserRepository springDataJpaUserRepository, UserJpaMapper userMapper) {
    this.springDataJpaUserRepository = springDataJpaUserRepository;
    this.userMapper = userMapper;
  }

  @Override
  public Optional<User> findByUsername(String username) {
    Optional<UserJpaEntity> jpaEntity = springDataJpaUserRepository.findByUsername(username);
    Optional<User> user = jpaEntity.map(entity -> userMapper.jpaToDomainEntity(entity));

    return user;
  }

  @Override
  public void save(User user) {
    UserJpaEntity jpaEntity = userMapper.domainToJpaEntity(
        user.getRole(), user.getPersonalDetails(), user.getContactInformation(), user);

    springDataJpaUserRepository.save(jpaEntity);
  }
}
