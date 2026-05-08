package com.javv.inventorySystem.presentation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javv.inventorySystem.application.service.RoleService;
import com.javv.inventorySystem.domain.model.role.Role;
import com.javv.inventorySystem.presentation.payload.ApiResponse;

@RestController
@RequestMapping(value = "api/v1/role")
public class RoleController {

  private RoleService roleService;

  public RoleController(RoleService roleService) {
    this.roleService = roleService;
  }

  @GetMapping("/getRoleById/{id}")
  public ApiResponse<Role> getRoleById(@PathVariable int id) {
    Role role = roleService.getRoleById(id);
    return ApiResponse.success(role, "Successfuly retrieved the role.", HttpStatus.FOUND.value());
  }

  @GetMapping("/getRoleByName/{name}")
  public ApiResponse<Role> getRoleByName(@PathVariable String name) {
    Role role = roleService.getRoleByName(name);
    return ApiResponse.success(role, "Successfully retrieved the role.", HttpStatus.FOUND.value());
  }
}
