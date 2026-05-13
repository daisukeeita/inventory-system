package com.javv.inventorySystem.presentation.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.javv.inventorySystem.application.command.user.UserRegisterCommand;
import com.javv.inventorySystem.application.service.UserService;
import com.javv.inventorySystem.domain.model.user.User;
import com.javv.inventorySystem.presentation.shared.payload.ApiResponse;
import com.javv.inventorySystem.presentation.user.dto.UserRegistrationDto;
import com.javv.inventorySystem.presentation.user.dto.UserResponseDto;

@RestController
@RequestMapping(value = "api/v1/user")
public class UserController {
  private UserService userService;
  private UserDtoMapper userDtoMapper;

  public UserController(UserService userService, UserDtoMapper userDtoMapper) {
    this.userService = userService;
    this.userDtoMapper = userDtoMapper;
  }

  @PostMapping("/save")
  @ResponseStatus(HttpStatus.CREATED)
  public ApiResponse<UserResponseDto> saveUser(
      @RequestBody UserRegistrationDto userRegistrationDto) {

    UserRegisterCommand userRegisterCommand = userDtoMapper.toCommandRecord(userRegistrationDto);

    User user = userService.saveUser(userRegisterCommand);

    UserResponseDto userResponseDto = userDtoMapper.toDtoEntity(user);

    return ApiResponse.success(
        userResponseDto, "Successfully created new user.", HttpStatus.CREATED.value());
  }

  @GetMapping("/getByUsername/{username}")
  @ResponseStatus(HttpStatus.FOUND)
  public ApiResponse<UserResponseDto> getByUsername(@PathVariable String username) {

    User user = userService.getUserByUsername(username);

    UserResponseDto userResponseDto = userDtoMapper.toDtoEntity(user);

    return ApiResponse.success(
        userResponseDto, "Successfully found the user", HttpStatus.FOUND.value());
  }
}
