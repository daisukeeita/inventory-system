package com.javv.inventorySystem.infrastructure.persistence.user;

import java.util.Optional;

import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import com.javv.inventorySystem.domain.exception.RoleAlreadyExistsException;
import com.javv.inventorySystem.domain.exception.SystemUnavailableException;
import com.javv.inventorySystem.domain.model.user.User;
import com.javv.inventorySystem.domain.repository.UserRepositoryInterface;

/**
 * This is where the system "translates" the JPA Entity (User) to Domain Entity (User) after getting
 * the data from the database.
 *
 * <p>This is where the system "translates" the Domain Entity (User) to JPA Entity (User) before
 * inserting the object to the database.
 */
@Repository
public class UserPersistenceAdapter implements UserRepositoryInterface {
  private final UserJpaRepository userJpaRepository;
  private final UserPersistenceMapper userMapper;

  public UserPersistenceAdapter(
      UserJpaRepository userJpaRepository, UserPersistenceMapper userPersistenceMapper) {
    this.userJpaRepository = userJpaRepository;
    this.userMapper = userPersistenceMapper;
  }

  @Override
  public Optional<User> findByUsername(String username) {
    Optional<UserJpaEntity> jpaEntity = userJpaRepository.findByUsername(username);
    Optional<User> user = jpaEntity.map(entity -> userMapper.jpaToDomainEntity(entity));

    return user;
  }

  @Override
  public void save(User user) {
    UserJpaEntity jpaEntity =
        userMapper.domainToJpaEntity(
            user.getRole(), user.getPersonalDetails(), user.getContactInformation(), user);
    try {
      userJpaRepository.save(jpaEntity);
    } catch (DataIntegrityViolationException exception) {
      throw new RoleAlreadyExistsException("Role Name already in use", exception);
    } catch (DataAccessResourceFailureException exception) {
      throw new SystemUnavailableException("Persistence service is down", exception);
    }
  }
}
