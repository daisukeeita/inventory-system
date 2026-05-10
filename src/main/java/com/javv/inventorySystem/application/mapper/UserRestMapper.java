package com.javv.inventorySystem.application.mapper;

import org.springframework.stereotype.Component;

import com.javv.inventorySystem.application.dto.user.UserCreateDto;
import com.javv.inventorySystem.domain.model.role.Role;
import com.javv.inventorySystem.domain.model.user.ContactInformation;
import com.javv.inventorySystem.domain.model.user.PersonalDetails;
import com.javv.inventorySystem.domain.model.user.User;

@Component
public class UserRestMapper {

  public User toDomainEntity(UserCreateDto userCreateDto, String hashedPassword, Role role) {

    User user = new User();

    user.setUsername(userCreateDto.getUsername());
    user.setHashedPassword(hashedPassword);
    user.setRole(role);
    user.setPersonalDetails(personalDetailsDtoToDomainEntity(userCreateDto));
    user.setContactInformation(contactInformationDtoToDomainEntity(userCreateDto));

    return user;
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
}
