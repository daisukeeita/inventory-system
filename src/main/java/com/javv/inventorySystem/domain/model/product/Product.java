package com.javv.inventorySystem.domain.model.product;

import java.time.Instant;

import com.javv.inventorySystem.domain.model.supplier.Supplier;

public class Product {
  private Long id;
  private String sku;
  private String name;
  private Supplier supplier;
  private UnitsOfMeasure baseUom;
  private Instant createdAt;
  private Instant updatedAt;

  public Product() {
  }

  public Product(String sku, String name, Supplier supplier, UnitsOfMeasure baseUom) {
    this.sku = sku;
    this.name = name;
    this.supplier = supplier;
    this.baseUom = baseUom;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setSku(String sku) {
    this.sku = sku;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setSupplier(Supplier supplier) {
    this.supplier = supplier;
  }

  public void setUnitsOfMeasure(UnitsOfMeasure baseUom) {
    this.baseUom = baseUom;
  }

  public void setCreatedAt(Instant createdAt) {
    this.createdAt = createdAt;
  }

  public void setUpdatedAt(Instant updatedAt) {
    this.updatedAt = updatedAt;
  }

  public Long getId() {
    return id;
  }

  public String getSku() {
    return sku;
  }

  public String getName() {
    return name;
  }

  public Supplier getSupplier() {
    return supplier;
  }

  public UnitsOfMeasure getBaseUnitsOfMeasure() {
    return baseUom;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }
}
