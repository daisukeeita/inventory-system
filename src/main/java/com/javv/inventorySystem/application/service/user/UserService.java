package com.javv.inventorySystem.application.service.user;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javv.inventorySystem.application.command.user.UserRegisterCommand;
import com.javv.inventorySystem.domain.exception.ResourceNotFoundException;
import com.javv.inventorySystem.domain.model.role.Role;
import com.javv.inventorySystem.domain.model.user.ContactInformation;
import com.javv.inventorySystem.domain.model.user.PersonalDetails;
import com.javv.inventorySystem.domain.model.user.User;
import com.javv.inventorySystem.domain.model.user.UserStatus;
import com.javv.inventorySystem.domain.repository.UserRepositoryInterface;

@Service
@Transactional(readOnly = true)
public class UserService {

  private RoleService roleService;
  private UserRepositoryInterface userRepositoryInterface;

  public UserService(RoleService roleService, UserRepositoryInterface userRepositoryInterface) {
    this.roleService = roleService;
    this.userRepositoryInterface = userRepositoryInterface;
  }

  @Transactional
  public User saveUser(UserRegisterCommand userRegisterCommand) {
    Role role = roleService.getRoleByName(userRegisterCommand.roleName());

    User user = toDomainEntity(userRegisterCommand, role);

    User savedUser = userRepositoryInterface.save(user);

    return savedUser;
  }

  public User getUserByUsername(String username) {
    Optional<User> optionalUser = userRepositoryInterface.findByUsername(username);

    User user = optionalUser.orElseThrow(
        () -> new ResourceNotFoundException(
            "User not found in database by the username: '" + username + "'"));

    return user;
  }

  private User toDomainEntity(UserRegisterCommand userRegisterCommand, Role role) {
    PersonalDetails personalDetails = new PersonalDetails();
    personalDetails.setFirstName(userRegisterCommand.firstName());
    personalDetails.setMiddleInitial(userRegisterCommand.middleInitial());
    personalDetails.setLastName(userRegisterCommand.lastName());
    personalDetails.setProfilePicture(userRegisterCommand.profilePicture());
    personalDetails.setDisplayName();

    ContactInformation contactInformation = new ContactInformation();
    contactInformation.setPhoneNumber(userRegisterCommand.phoneNumber());
    contactInformation.setEmail(userRegisterCommand.email());
    contactInformation.setMailingAddress(userRegisterCommand.mailingAddress());

    User user = new User();
    user.setUsername(userRegisterCommand.username());
    user.setHashedPassword(userRegisterCommand.hashedPassword());
    user.setRole(role);
    user.setUserStatus(UserStatus.OFFLINE);
    user.setPersonalDetails(personalDetails);
    user.setContactInformation(contactInformation);

    return user;
  }
}
