package com.javv.inventorySystem.infrastructure.persistence.product;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.javv.inventorySystem.infrastructure.persistence.productPackaging.ProductPackagingJpaEntity;
import com.javv.inventorySystem.infrastructure.persistence.supplier.SupplierJpaEntity;
import com.javv.inventorySystem.infrastructure.persistence.unitsOfMeasure.UnitsOfMeasureJpaEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
@EntityListeners(AuditingEntityListener.class)
public class ProductJpaEntity {
  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "sku", nullable = false, unique = true)
  private String sku;

  @Column(name = "name", nullable = false, length = 50)
  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "supplier_id", nullable = false)
  private SupplierJpaEntity supplierJpaEntity;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "base_uom_id", nullable = false)
  private UnitsOfMeasureJpaEntity baseUnitsOfMeasure;

  @OneToMany(mappedBy = "productJpaEntity", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ProductPackagingJpaEntity> listProductPackages = new ArrayList<ProductPackagingJpaEntity>();

  @CreatedDate
  @Column(name = "created_at", nullable = false, updatable = false)
  private Instant createdAt;

  @LastModifiedDate
  @Column(name = "updated_at", nullable = false)
  private Instant updatedAt;

  public ProductJpaEntity() {
  }

  public ProductJpaEntity(
      String sku,
      String name,
      SupplierJpaEntity supplierJpaEntity,
      UnitsOfMeasureJpaEntity unitsOfMeasureJpaEntity) {
    this.sku = sku;
    this.name = name;
    this.supplierJpaEntity = supplierJpaEntity;
    this.baseUnitsOfMeasure = unitsOfMeasureJpaEntity;
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

  public void setSupplier(SupplierJpaEntity supplierJpaEntity) {
    this.supplierJpaEntity = supplierJpaEntity;
  }

  public void setBaseUom(UnitsOfMeasureJpaEntity unitsOfMeasureJpaEntity) {
    this.baseUnitsOfMeasure = unitsOfMeasureJpaEntity;
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

  public String getSku() {
    return sku;
  }

  public String getName() {
    return name;
  }

  public SupplierJpaEntity getSupplier() {
    return supplierJpaEntity;
  }

  public UnitsOfMeasureJpaEntity getBaseUnitOfMeasure() {
    return baseUnitsOfMeasure;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }
}
