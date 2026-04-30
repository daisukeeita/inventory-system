package com.javv.inventorySystem.domain.model.user;

public class Role {
  private int id;
  private String name;

  public void setId(int id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }
}
