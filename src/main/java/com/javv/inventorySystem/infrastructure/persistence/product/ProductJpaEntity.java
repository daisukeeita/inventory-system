package com.javv.inventorySystem.infrastructure.persistence.product;

import com.javv.inventorySystem.infrastructure.persistence.supplier.SupplierJpaEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class ProductJpaEntity {
  @Id
  @Column(name = "sku", nullable = false, length = 50)
  private String sku;

  @Column(name = "name", nullable = false, length = 50)
  private String name;

  @ManyToOne
  @JoinColumn(name = "supplier_id", nullable = false)
  private SupplierJpaEntity supplierJpaEntity;

  @OneToOne
  @JoinColumn(name = "base_uom_id", nullable = false)
  private UnitsOfMeasureJpaEntity baseUnitsOfMeasure;

  public ProductJpaEntity() {
  }

  public ProductJpaEntity(
      String sku, String name, SupplierJpaEntity supplierJpaEntity,
      UnitsOfMeasureJpaEntity unitsOfMeasureJpaEntity) {
    this.sku = sku;
    this.name = name;
    this.supplierJpaEntity = supplierJpaEntity;
    this.baseUnitsOfMeasure = unitsOfMeasureJpaEntity;
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
}
