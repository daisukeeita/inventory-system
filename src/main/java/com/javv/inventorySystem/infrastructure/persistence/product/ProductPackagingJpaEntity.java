package com.javv.inventorySystem.infrastructure.persistence.product;

import java.math.BigDecimal;

import com.javv.inventorySystem.infrastructure.persistence.unitsOfMeasure.UnitsOfMeasureJpaEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
    name = "product_packaging",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"product_sku", "uom_id"})})
public class ProductPackagingJpaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private int id;

  @Column(name = "packaging_code", nullable = false, length = 20)
  private String packagingCode;

  @ManyToOne
  @JoinColumn(name = "product_sku", nullable = false)
  private ProductJpaEntity productJpaEntity;

  @ManyToOne
  @JoinColumn(name = "uom_id", nullable = false)
  private UnitsOfMeasureJpaEntity unitsOfMeasureJpaEntity;

  @Column(name = "conversion_factor", nullable = false)
  private int conversionFactor;

  @Column(name = "price", nullable = false)
  private BigDecimal price;

  public ProductPackagingJpaEntity() {}

  public ProductPackagingJpaEntity(
      String packagingCode,
      ProductJpaEntity productJpaEntity,
      UnitsOfMeasureJpaEntity unitsOfMeasureJpaEntity,
      int conversionFactor,
      BigDecimal price) {
    this.packagingCode = packagingCode;
    this.productJpaEntity = productJpaEntity;
    this.unitsOfMeasureJpaEntity = unitsOfMeasureJpaEntity;
    this.conversionFactor = conversionFactor;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setPackagingCode(String packagingCode) {
    this.packagingCode = packagingCode;
  }

  public void setProduct(ProductJpaEntity productJpaEntity) {
    this.productJpaEntity = productJpaEntity;
  }

  public void setUnitsOfMeasure(UnitsOfMeasureJpaEntity unitsOfMeasureJpaEntity) {
    this.unitsOfMeasureJpaEntity = unitsOfMeasureJpaEntity;
  }

  public void setConversionFactor(int conversionFactor) {
    this.conversionFactor = conversionFactor;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public int getId() {
    return id;
  }

  public String getPackagingCode() {
    return packagingCode;
  }

  public ProductJpaEntity getProduct() {
    return productJpaEntity;
  }

  public UnitsOfMeasureJpaEntity getUnitsOfMeasure() {
    return unitsOfMeasureJpaEntity;
  }

  public int getConversionFactor() {
    return conversionFactor;
  }

  public BigDecimal getPrice() {
    return price;
  }
}
