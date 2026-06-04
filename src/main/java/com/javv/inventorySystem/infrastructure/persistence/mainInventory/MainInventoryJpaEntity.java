package com.javv.inventorySystem.infrastructure.persistence.mainInventory;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.javv.inventorySystem.infrastructure.persistence.product.ProductJpaEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "main_inventory")
@EntityListeners(AuditingEntityListener.class)
public class MainInventoryJpaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @OneToOne
  @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
  private ProductJpaEntity productJpaEntity;

  @Column(name = "quantity_on_hand", nullable = false)
  private int quantityOnHand;

  @Column(name = "reorder_level", nullable = false)
  private int reorderLevel;

  @CreatedDate
  @Column(name = "created_at", nullable = false, updatable = false)
  private Instant createdAt;

  @LastModifiedDate
  @Column(name = "updated_at", nullable = false)
  private Instant updatedAt;

  public MainInventoryJpaEntity() {
  }

  public MainInventoryJpaEntity(
      ProductJpaEntity productJpaEntity, int quantityOnHand, int reorderLevel) {
    this.productJpaEntity = productJpaEntity;
    this.quantityOnHand = quantityOnHand;
    this.reorderLevel = reorderLevel;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setProduct(ProductJpaEntity productJpaEntity) {
    this.productJpaEntity = productJpaEntity;
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

  public ProductJpaEntity getProduct() {
    return productJpaEntity;
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
