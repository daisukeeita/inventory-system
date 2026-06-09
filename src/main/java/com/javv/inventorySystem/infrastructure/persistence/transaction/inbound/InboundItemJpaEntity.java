package com.javv.inventorySystem.infrastructure.persistence.transaction.inbound;

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
  @JoinColumn(name = "inbound_id", referencedColumnName = "id", nullable = false)
  private InboundJpaEntity inboundJpaEntity;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
  private ProductJpaEntity productJpaEntity;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "packaging_id", referencedColumnName = "id", nullable = false)
  private ProductPackagingJpaEntity productPackagingJpaEntity;

  @Column(name = "quantity_received", nullable = false)
  private int quantityReceived;

  @Column(name = "base_quantity_equivalent", nullable = false)
  private int baseQuantityEquivalent;

  public InboundItemJpaEntity() {
  }

  public InboundItemJpaEntity(
      ProductJpaEntity productJpaEntity,
      ProductPackagingJpaEntity productPackagingJpaEntity,
      int quantityReceived,
      int baseQuantityEquivalent) {
    this.productJpaEntity = productJpaEntity;
    this.quantityReceived = quantityReceived;
    this.baseQuantityEquivalent = baseQuantityEquivalent;
    this.productPackagingJpaEntity = productPackagingJpaEntity;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setInbound(InboundJpaEntity inboundJpaEntity) {
    this.inboundJpaEntity = inboundJpaEntity;
  }

  public void setProduct(ProductJpaEntity productJpaEntity) {
    this.productJpaEntity = productJpaEntity;
  }

  public void setProductPackaging(ProductPackagingJpaEntity packagingType) {
    this.productPackagingJpaEntity = packagingType;
  }

  public void setQuantityReceived(int quantityReceived) {
    this.quantityReceived = quantityReceived;
  }

  public void setBaseQuantityEquivalent(int baseQuantityEquivalent) {
    this.baseQuantityEquivalent = baseQuantityEquivalent;
  }

  public Long getId() {
    return id;
  }

  public ProductJpaEntity getProduct() {
    return productJpaEntity;
  }

  public ProductPackagingJpaEntity getProductPackagingJpaEntity() {
    return productPackagingJpaEntity;
  }

  public int getQuantityReceived() {
    return quantityReceived;
  }

  public int getBaseQuantityEquivalent() {
    return baseQuantityEquivalent;
  }
}
