package com.javv.inventorySystem.presentation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.javv.inventorySystem.application.dto.user.UserCreateDto;
import com.javv.inventorySystem.application.dto.user.UserResponseDto;
import com.javv.inventorySystem.application.service.UserService;
import com.javv.inventorySystem.presentation.payload.ApiResponse;

@RestController
@RequestMapping(value = "api/v1/user")
public class UserController {
  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/save")
  @ResponseStatus(HttpStatus.CREATED)
  public ApiResponse<UserResponseDto> saveUser(@RequestBody UserCreateDto userCreateDto) {
    UserResponseDto userResponseDto = userService.saveUser(userCreateDto);
    return ApiResponse.success(
        userResponseDto, "Successfully created new user.", HttpStatus.CREATED.value());
  }
}
