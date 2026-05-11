package com.javv.inventorySystem.application.mapper;

import org.springframework.stereotype.Component;

import com.javv.inventorySystem.application.dto.user.UserCreateDto;
import com.javv.inventorySystem.application.dto.user.UserResponseDto;
import com.javv.inventorySystem.domain.model.role.Role;
import com.javv.inventorySystem.domain.model.user.ContactInformation;
import com.javv.inventorySystem.domain.model.user.PersonalDetails;
import com.javv.inventorySystem.domain.model.user.User;
import com.javv.inventorySystem.domain.model.user.UserStatus;

@Component
public class UserRestMapper {

  public User toDomainEntity(UserCreateDto userCreateDto, String hashedPassword, Role role) {

    User user = new User();

    user.setUsername(userCreateDto.getUsername());
    user.setHashedPassword(hashedPassword);
    user.setUserStatus(setStatus(userCreateDto.getUserStatus()));
    user.setRole(role);
    user.setPersonalDetails(personalDetailsDtoToDomainEntity(userCreateDto));
    user.setContactInformation(contactInformationDtoToDomainEntity(userCreateDto));

    return user;
  }

  public UserResponseDto toDtoEntity(User user) {
    UserResponseDto userResponseDto = new UserResponseDto();

    userResponseDto.setId(user.getId());
    userResponseDto.setUsername(user.getUsername());
    userResponseDto.setIsActive(user.getIsActive());

    userResponseDto.setRole(user.getRole().getName());

    userResponseDto.setDisplayName(user.getPersonalDetails().getDisplayName());
    userResponseDto.setProfilePicture(user.getPersonalDetails().getProfilePicture());

    userResponseDto.setEmail(user.getContactInformation().getEmail());
    userResponseDto.setPhoneNumber(user.getContactInformation().getPhoneNumber());

    userResponseDto.setStatus(user.getUserStatus().toString());

    return userResponseDto;
  }

  private ContactInformation contactInformationDtoToDomainEntity(UserCreateDto userCreateDto) {
    ContactInformation contactInformation = new ContactInformation();
    contactInformation.setEmail(userCreateDto.getEmail());
    contactInformation.setPhoneNumber(userCreateDto.getPhoneNumber());
    contactInformation.setMailingAddress(userCreateDto.getMailingAddress());

    return contactInformation;
  }

  private PersonalDetails personalDetailsDtoToDomainEntity(UserCreateDto userCreateDto) {
    PersonalDetails personalDetails = new PersonalDetails();
    personalDetails.setFirstName(userCreateDto.getFirstName());
    personalDetails.setMiddleInitial(userCreateDto.getMiddleInitial());
    personalDetails.setLastName(userCreateDto.getLastName());
    personalDetails.setProfilePicture(userCreateDto.getProfilePicture());
    personalDetails.setDisplayName();

    return personalDetails;
  }

  private UserStatus setStatus(String status) {
    switch (status.toUpperCase()) {
      case "ONLINE":
        return UserStatus.ONLINE;
      case "OFFLINE":
        return UserStatus.OFFLINE;
      default:
        throw new IllegalArgumentException("Given status is invalid: " + status);
    }
  }
}
