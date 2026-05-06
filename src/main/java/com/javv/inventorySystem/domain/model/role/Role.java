package com.javv.inventorySystem.domain.model.role;

public class Role {
  private int id = 0;
  private String name;

  public void setId(int id) {
    if (id == 0 || id == -1) {
      throw new IllegalArgumentException("Role ID should not be empty.");
    }
    this.id = id;
  }

  public void setName(String name) {
    if (name == null || name.trim().isBlank()) {
      throw new IllegalArgumentException("Role Name should not be empty.");
    }
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }
}
