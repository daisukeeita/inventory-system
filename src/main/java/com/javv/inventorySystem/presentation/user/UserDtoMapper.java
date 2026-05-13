package com.javv.inventorySystem.presentation.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.javv.inventorySystem.application.command.user.UserRegisterCommand;
import com.javv.inventorySystem.domain.model.user.User;
import com.javv.inventorySystem.presentation.user.dto.UserRegistrationDto;
import com.javv.inventorySystem.presentation.user.dto.UserResponseDto;

@Component
public class UserDtoMapper {

  private BCryptPasswordEncoder passwordEncoder;

  public UserDtoMapper(BCryptPasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  public UserRegisterCommand toCommandRecord(UserRegistrationDto userRegistrationDto) {
    String hashedPassword = passwordEncoder.encode(userRegistrationDto.password());

    return new UserRegisterCommand(
        userRegistrationDto.username(),
        hashedPassword,
        userRegistrationDto.roleName(),
        userRegistrationDto.firstName(),
        userRegistrationDto.middleInitial(),
        userRegistrationDto.lastName(),
        userRegistrationDto.profilePicture(),
        userRegistrationDto.email(),
        userRegistrationDto.phoneNumber(),
        userRegistrationDto.mailingAddress());
  }

  public UserResponseDto toDtoEntity(User user) {

    return new UserResponseDto(
        user.getId(),
        user.getUsername(),
        user.getPersonalDetails().getDisplayName(),
        user.getRole().getName(),
        user.getPersonalDetails().getProfilePicture(),
        user.getContactInformation().getEmail(),
        user.getContactInformation().getPhoneNumber(),
        user.getIsActive(),
        user.getUserStatus().toString());
  }
}
