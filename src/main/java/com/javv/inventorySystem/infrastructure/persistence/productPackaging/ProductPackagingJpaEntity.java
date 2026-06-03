package com.javv.inventorySystem.infrastructure.persistence.productPackaging;

import java.math.BigDecimal;

import com.javv.inventorySystem.infrastructure.persistence.product.ProductJpaEntity;
import com.javv.inventorySystem.infrastructure.persistence.unitsOfMeasure.UnitsOfMeasureJpaEntity;

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
@Table(name = "product_packaging")
public class ProductPackagingJpaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "packaging_code", nullable = false, length = 20)
  private String packagingCode;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
  private ProductJpaEntity productJpaEntity;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "uom_id", referencedColumnName = "id", nullable = false)
  private UnitsOfMeasureJpaEntity unitsOfMeasureJpaEntity;

  @Column(name = "conversion_factor", nullable = false)
  private int conversionFactor;

  @Column(name = "price", nullable = false)
  private BigDecimal price;

  public ProductPackagingJpaEntity() {
  }

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

  public void setId(Long id) {
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

  public Long getId() {
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
