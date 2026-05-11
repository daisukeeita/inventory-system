package com.javv.inventorySystem.domain.model.product;

public class UnitsOfMeasure {
  private int id;
  private String name;
  private String abbreviation;

  public UnitsOfMeasure() {}

  public UnitsOfMeasure(String name, String abbreviation) {
    this.name = name;
    this.abbreviation = abbreviation;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setAbbreviation(String abbreviation) {
    this.abbreviation = abbreviation;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getAbbreviation() {
    return abbreviation;
  }
}
