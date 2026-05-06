package com.javv.inventorySystem.presentation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javv.inventorySystem.application.service.RoleService;
import com.javv.inventorySystem.domain.model.role.Role;

@RestController
@RequestMapping(value = "api/v1/role")
public class RoleController {

  private RoleService roleService;

  public RoleController(RoleService roleService) {
    this.roleService = roleService;
  }

  @GetMapping("/getRole/{id}")
  public Role getRoleById(@PathVariable int id) {
    return roleService.getRoleById(id);
  }
}
