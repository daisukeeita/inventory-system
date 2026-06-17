package com.javv.inventorySystem.domain.model.role;

public class Role {
  private Integer id;
  private String name;

  public void setId(int id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }
}
