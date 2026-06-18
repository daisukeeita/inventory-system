package com.javv.inventorySystem.infrastructure.persistence.user;

import java.util.Optional;

import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Repository;

import com.javv.inventorySystem.domain.exception.SystemUnavailableException;
import com.javv.inventorySystem.domain.model.user.User;
import com.javv.inventorySystem.domain.repository.UserRepositoryInterface;

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
public class UserPersistenceAdapter implements UserRepositoryInterface {
  private final UserJpaRepository userJpaRepository;
  private final UserPersistenceMapper userPersistenceMapper;

  public UserPersistenceAdapter(
      UserJpaRepository userJpaRepository, UserPersistenceMapper userPersistenceMapper) {
    this.userJpaRepository = userJpaRepository;
    this.userPersistenceMapper = userPersistenceMapper;
  }

  @Override
  public User save(User user) {
    UserJpaEntity jpaEntity = userPersistenceMapper.toJpaEntity(user);
    UserJpaEntity savedEntity;

    try {
      savedEntity = userJpaRepository.save(jpaEntity);
    } catch (DataAccessResourceFailureException exception) {
      throw new SystemUnavailableException("Persistence service is down", exception);
    }

    return userPersistenceMapper.toDomainEntity(savedEntity);
  }

  @Override
  public User update(User user) {
    // TODO: Update this method

    return null;
  }

  @Override
  public Optional<User> findByUsername(String username) {
    Optional<UserJpaEntity> jpaEntity = userJpaRepository.findByUsername(username);

    Optional<User> user = jpaEntity.map(entity -> userPersistenceMapper.toDomainEntity(entity));

    return user;
  }

  @Override
  public boolean existsByUsername(String username) {
    return userJpaRepository.existsByUsername(username);
  }
}
