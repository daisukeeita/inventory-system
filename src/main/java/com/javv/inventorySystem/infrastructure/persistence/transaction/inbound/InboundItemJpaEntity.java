package com.javv.inventorySystem.infrastructure.persistence.transaction.inbound;

import java.time.Instant;

import com.javv.inventorySystem.infrastructure.persistence.product.ProductJpaEntity;
import com.javv.inventorySystem.infrastructure.persistence.productPackaging.ProductPackagingJpaEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "inbound_item")
public class InboundItemJpaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "inbound_id", nullable = false)
  private InboundJpaEntity inboundJpaEntity;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "product_sku", referencedColumnName = "sku", nullable = false)
  private ProductJpaEntity productJpaEntity;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "packaging_type", referencedColumnName = "packaging_code", nullable = false)
  private ProductPackagingJpaEntity packagingType;

  @Column(name = "quantity_received", nullable = false)
  private int quantityReceived;

  @Column(name = "base_quantity_equivalent", nullable = false)
  private int baseQuantityEquivalent;

  public InboundItemJpaEntity() {}

  public InboundItemJpaEntity(
      InboundJpaEntity inboundJpaEntity,
      ProductJpaEntity productJpaEntity,
      ProductPackagingJpaEntity productPackagingJpaEntity,
      int quantityReceived,
      int baseQuantityEquivalent) {
    this.inboundJpaEntity = inboundJpaEntity;
    this.productJpaEntity = productJpaEntity;
    this.quantityReceived = quantityReceived;
    this.baseQuantityEquivalent = baseQuantityEquivalent;
    this.productPackagingJpaEntity = productPackagingJpaEntity;
  }

  public void setInbound(InboundJpaEntity inboundJpaEntity) {
    this.inboundJpaEntity = inboundJpaEntity;
  }

  public void setProduct(ProductJpaEntity productJpaEntity) {
    this.productJpaEntity = productJpaEntity;
  }

  public void setProductPackaging(ProductPackagingJpaEntity productPackagingJpaEntity) {
    this.productPackagingJpaEntity = productPackagingJpaEntity;
  }

  public void setQuantityReceived(int quantityReceived) {
    this.quantityReceived = quantityReceived;
  }

  public void setBaseQuantityEquivalent(int baseQuantityEquivalent) {
    this.baseQuantityEquivalent = baseQuantityEquivalent;
  }

  protected void setCreatedAt(Instant createdAt) {
    this.createdAt = createdAt;
  }

  protected void setUpdatedAt(Instant updatedAt) {
    this.updatedAt = updatedAt;
  }

  public Long getId() {
    return id;
  }

  public ProductJpaEntity getProduct() {
    return productJpaEntity;
  }

  public ProductPackagingJpaEntity getProductPackaging() {
    return productPackagingJpaEntity;
  }

  public int getQuantityReceived() {
    return quantityReceived;
  }

  public int getBaseQuantityEquivalent() {
    return baseQuantityEquivalent;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }
}
