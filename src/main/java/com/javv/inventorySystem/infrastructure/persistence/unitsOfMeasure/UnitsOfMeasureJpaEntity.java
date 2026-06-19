package com.javv.inventorySystem.infrastructure.persistence.unitsOfMeasure;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

@Entity
@Table(name = "units_of_measure", indexes = {
    @Index(name = "idx_units_of_measure_name", columnList = "name")
})
@EntityListeners(AuditingEntityListener.class)
public class UnitsOfMeasureJpaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "name", nullable = false, length = 20, unique = true)
  private String name;

  @Column(name = "abbreviation", nullable = true, length = 10)
  private String abbreviation;

  @CreatedDate
  @Column(name = "created_at", nullable = false, updatable = false)
  private Instant createdAt;

  @LastModifiedDate
  @Column(name = "updated_at", nullable = false)
  private Instant updatedAt;

  public UnitsOfMeasureJpaEntity() {
  }

  public UnitsOfMeasureJpaEntity(String name, String abbreviation) {
    this.name = name;
    this.abbreviation = abbreviation;
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

  protected void setCreatedAt(Instant createdAt) {
    this.createdAt = createdAt;
  }

  protected void setUpdatedAt(Instant updatedAt) {
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
