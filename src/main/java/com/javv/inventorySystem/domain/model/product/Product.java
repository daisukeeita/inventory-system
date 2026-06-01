package com.javv.inventorySystem.domain.model.product;

import java.time.Instant;

public class Product {
  private Long id;
  private String sku;
  private String name;
  private int supplierId;
  private int baseUomId;
  private Instant createdAt;
  private Instant updatedAt;

  public Product() {
  }

  public Product(String sku, String name, int supplierId, int baseUomId) {
    this.sku = sku;
    this.name = name;
    this.supplierId = supplierId;
    this.baseUomId = baseUomId;
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

  public void setSupplierId(int supplierId) {
    this.supplierId = supplierId;
  }

  public void setUnitsOfMeasureId(int baseUomId) {
    this.baseUomId = baseUomId;
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

  public int getSupplierId() {
    return supplierId;
  }

  public int getBaseUnitsOfMeasureId() {
    return baseUomId;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }
}
