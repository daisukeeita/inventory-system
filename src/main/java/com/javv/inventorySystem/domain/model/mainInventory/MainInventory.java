package com.javv.inventorySystem.domain.model.mainInventory;

import java.time.Instant;

public class MainInventory {

  private Integer id;
  private String productSku;
  private int quantityOnHand;
  private int reorderLevel = 100;
  private Instant createdAt;
  private Instant updatedAt;

  public MainInventory() {
  }

  public MainInventory(Integer id, String productSku, int quantityOnHand, int reorderLevel) {
    if (productSku == null) {
      throw new IllegalArgumentException("Main Inventory Domain: Product is required.");
    }

    if (quantityOnHand < 0 || reorderLevel < 0) {
      throw new IllegalArgumentException(
          "Main Inventory Domain: Inventory counts cannot be negative.");
    }
    this.id = id;
    this.productSku = productSku;
    this.quantityOnHand = quantityOnHand;
    this.reorderLevel = reorderLevel;
  }

  public void deductStock(int amount) {
    if (amount <= 0) {
      throw new IllegalArgumentException(
          "Main Inventory Domain: Deduction must be greater than zero.");
    }
    if (this.quantityOnHand - amount < 0) {
      throw new IllegalStateException("Main Inventory Domain: Insufficient Stock.");
    }
    this.quantityOnHand -= amount;
  }

  public void increaseStock(int amount) {
    this.quantityOnHand += amount;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setProductSku(String productSku) {
    this.productSku = productSku;
  }

  public void setQuantityOnHand(int quantityOnHand) {
    this.quantityOnHand = quantityOnHand;
  }

  public void setReorderLevel(int reorderLevel) {
    this.reorderLevel = reorderLevel;
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

  public String getProductSku() {
    return productSku;
  }

  public int getQuantityOnHand() {
    return quantityOnHand;
  }

  public int getReorderLevel() {
    return reorderLevel;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }
}
