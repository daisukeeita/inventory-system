package com.javv.inventorySystem.infrastructure.persistence.user;

import org.springframework.stereotype.Component;

import com.javv.inventorySystem.domain.model.role.Role;
import com.javv.inventorySystem.domain.model.user.ContactInformation;
import com.javv.inventorySystem.domain.model.user.PersonalDetails;
import com.javv.inventorySystem.domain.model.user.User;
import com.javv.inventorySystem.infrastructure.persistence.role.RoleJpaEntity;

@Component
public class UserPersistenceMapper {
  public User toDomainEntity(UserJpaEntity userJpaEntity) {
    Role role = new Role();

    role.setId(userJpaEntity.getRole().getId());
    role.setName(userJpaEntity.getRole().getName());

    PersonalDetails personalDetails = new PersonalDetails();
    personalDetails.setUserId(userJpaEntity.getPersonalDetailsJpaEntity().getUserId());
    personalDetails.setFirstName(userJpaEntity.getPersonalDetailsJpaEntity().getFirstName());
    personalDetails.setMiddleInitial(
        userJpaEntity.getPersonalDetailsJpaEntity().getMiddleInitial());
    personalDetails.setLastName(userJpaEntity.getPersonalDetailsJpaEntity().getLastName());
    personalDetails.setFullName(userJpaEntity.getPersonalDetailsJpaEntity().getDisplayName());
    personalDetails.setProfilePicture(
        userJpaEntity.getPersonalDetailsJpaEntity().getProfilePicture());

    ContactInformation contactInformation = new ContactInformation();
    contactInformation.setUserId(userJpaEntity.getContactInformationJpaEntity().getUserId());
    contactInformation.setEmail(userJpaEntity.getContactInformationJpaEntity().getEmail());
    contactInformation.setMailingAddress(
        userJpaEntity.getContactInformationJpaEntity().getMailingAddress());
    contactInformation.setPhoneNumber(
        userJpaEntity.getContactInformationJpaEntity().getPhoneNumber());

    User user = new User();
    user.setId(userJpaEntity.getUserId());
    user.setUsername(userJpaEntity.getUsername());
    user.setHashedPassword(userJpaEntity.getHashedPassword());
    user.setRole(role);
    user.setPersonalDetails(personalDetails);

    return user;
  }

  public UserJpaEntity toJpaEntity(User user) {
    RoleJpaEntity roleJpaEntity = new RoleJpaEntity();
    UserJpaEntity userJpaEntity = new UserJpaEntity();

    roleJpaEntity.setId(user.getRole().getId());
    roleJpaEntity.setName(user.getRole().getName());

    ContactInformationJpaEntity contactInformationJpaEntity = new ContactInformationJpaEntity();
    contactInformationJpaEntity.setUser(userJpaEntity);
    contactInformationJpaEntity.setEmail(user.getContactInformation().getEmail());
    contactInformationJpaEntity.setPhoneNumber(user.getContactInformation().getPhoneNumber());
    contactInformationJpaEntity.setMailingAddress(user.getContactInformation().getMailingAddress());

    PersonalDetailsJpaEntity personalDetailsJpaEntity = new PersonalDetailsJpaEntity();
    personalDetailsJpaEntity.setUser(userJpaEntity);
    personalDetailsJpaEntity.setFirstName(user.getPersonalDetails().getFirstName());
    personalDetailsJpaEntity.setMiddleInitial(user.getPersonalDetails().getMiddleInitial());
    personalDetailsJpaEntity.setLastName(user.getPersonalDetails().getLastName());
    personalDetailsJpaEntity.setDisplayName(user.getPersonalDetails().getDisplayName());
    personalDetailsJpaEntity.setProfilePicture(user.getPersonalDetails().getProfilePicture());

    userJpaEntity.setUsername(user.getUsername());
    userJpaEntity.setHashedPassword(user.getHashedPassword());
    userJpaEntity.setRole(roleJpaEntity);
    userJpaEntity.setPersonalDetails(personalDetailsJpaEntity);
    userJpaEntity.setContactInformation(contactInformationJpaEntity);

    return userJpaEntity;
  }
}
