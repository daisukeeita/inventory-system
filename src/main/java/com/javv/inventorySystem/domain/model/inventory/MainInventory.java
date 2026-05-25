package com.javv.inventorySystem.domain.model.inventory;

import java.time.Instant;

import com.javv.inventorySystem.domain.model.product.Product;

public class MainInventory {

  private Integer id;
  private Product product;
  private int quantityOnHand;
  private int reorderLevel;
  private Instant createdAt;
  private Instant updatedAt;

  public MainInventory() {}

  public MainInventory(Integer id, Product product, int quantityOnHand, int reorderLevel) {
    if (product == null) {
      throw new IllegalArgumentException("Main Inventory Domain: Product is required.");
    }

    if (quantityOnHand < 0 || reorderLevel < 0) {
      throw new IllegalArgumentException(
          "Main Inventory Domain: Inventory counts cannot be negative.");
    }
    this.id = id;
    this.product = product;
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

  public void setProduct(Product product) {
    this.product = product;
  }

  public void setQuantityOnHand(int quantityOnHand) {
    this.quantityOnHand = quantityOnHand;
  }

  public void setReorderLevel(int reorderLevel) {
    this.reorderLevel = reorderLevel;
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

  public Product getProduct() {
    return product;
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
