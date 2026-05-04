package com.javv.inventorySystem.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.javv.inventorySystem.domain.model.user.ContactInformation;
import com.javv.inventorySystem.domain.model.user.PersonalDetails;
import com.javv.inventorySystem.domain.model.user.Role;
import com.javv.inventorySystem.domain.model.user.User;
import com.javv.inventorySystem.infrastructure.persistence.user.ContactInformationJpaEntity;
import com.javv.inventorySystem.infrastructure.persistence.user.PersonalDetailsJpaEntity;
import com.javv.inventorySystem.infrastructure.persistence.user.RoleJpaEntity;
import com.javv.inventorySystem.infrastructure.persistence.user.UserJpaEntity;

@Component
public class UserJpaMapper {
  public User jpaToDomainEntity(UserJpaEntity userJpaEntity) {
    Role role = new Role();
    role.setId(userJpaEntity.getRole().getId());
    role.setName(userJpaEntity.getRole().getName());

    PersonalDetails personalDetails = new PersonalDetails();
    personalDetails.setUserId(userJpaEntity.getUserId());
    personalDetails.setFirstName(userJpaEntity.getPersonalDetailsJpaEntity().getFirstName());
    personalDetails.setMiddleInitial(
        userJpaEntity.getPersonalDetailsJpaEntity().getMiddleInitial());
    personalDetails.setLastName(userJpaEntity.getPersonalDetailsJpaEntity().getLastName());
    personalDetails.setDisplayName();
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

  public UserJpaEntity domainToJpaEntity(
      Role role,
      PersonalDetails personalDetails,
      ContactInformation contactInformation,
      User user) {
    PersonalDetailsJpaEntity personalDetailsJpaEntity = new PersonalDetailsJpaEntity();
    personalDetailsJpaEntity.setDisplayName(personalDetails.getDisplayName());
    personalDetailsJpaEntity.setFirstName(personalDetails.getFirstName());
    personalDetailsJpaEntity.setMiddleInitial(personalDetails.getMiddleInitial());
    personalDetailsJpaEntity.setLastName(personalDetails.getLastName());
    personalDetailsJpaEntity.setProfilePicture(personalDetailsJpaEntity.getProfilePicture());

    ContactInformationJpaEntity contactInformationJpaEntity = new ContactInformationJpaEntity();
    contactInformationJpaEntity.setEmail(contactInformation.getEmail());
    contactInformationJpaEntity.setMailingAddress(contactInformation.getMailingAddress());
    contactInformationJpaEntity.setPhoneNumber(contactInformation.getPhoneNumber());

    RoleJpaEntity roleJpaEntity = new RoleJpaEntity();
    roleJpaEntity.setId(role.getId());
    roleJpaEntity.setName(role.getName());

    UserJpaEntity userJpaEntity = new UserJpaEntity();
    userJpaEntity.setUsername(user.getUsername());
    userJpaEntity.setHashedPassword(user.getHashedPassword());
    userJpaEntity.setRole(roleJpaEntity);
    userJpaEntity.setPersonalDetails(personalDetailsJpaEntity);
    userJpaEntity.setContactInformation(contactInformationJpaEntity);

    return userJpaEntity;
  }
}
