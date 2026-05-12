package com.javv.inventorySystem.infrastructure.persistence.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "units_of_measure")
public class UnitsOfMeasureJpaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private int id;

  @Column(name = "name", nullable = false, length = 20)
  private String name;

  @Column(name = "abbreviation", nullable = true, length = 10)
  private String abbreviation;

  public UnitsOfMeasureJpaEntity() {
  }

  public UnitsOfMeasureJpaEntity(
      String name, String abbreviation) {
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
