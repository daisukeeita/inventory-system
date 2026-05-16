package com.javv.inventorySystem.domain.model.product;

import java.time.Instant;

public class UnitsOfMeasure {
  private Integer id;
  private String name;
  private String abbreviation;
  private Instant createdAt;
  private Instant updatedAt;

  public UnitsOfMeasure() {}

  public UnitsOfMeasure(String name, String abbreviation, Instant createdAt, Instant updatedAt) {
    this.name = name;
    this.abbreviation = abbreviation;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setAbbreviation(String abbreviation) {
    this.abbreviation = abbreviation;
  }

  public void setCreatedAt(Instant createdAt) {
    this.createdAt = createdAt;
  }

  public void setUpdatedAt(Instant updatedAt) {
    this.updatedAt = updatedAt;
  }

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getAbbreviation() {
    return abbreviation;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }
}
