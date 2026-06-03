package com.javv.inventorySystem.domain.model.product;

import java.math.BigDecimal;

public class ProductPackaging {
  private Long id;
  private String packagingCode;
  private Long productId;
  private int unitsOfMeasureId;
  private int conversionFactor;
  private BigDecimal price;

  public ProductPackaging() {
  }

  public ProductPackaging(
      Long id,
      String packagingCode,
      Long productId,
      int unitsOfMeasureId,
      int conversionFactor,
      BigDecimal price) {
    this.id = id;
    this.packagingCode = packagingCode;
    this.productId = productId;
    this.unitsOfMeasureId = unitsOfMeasureId;
    this.conversionFactor = conversionFactor;
    this.price = price;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setPackagingCode(String packagingCode) {
    this.packagingCode = packagingCode;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public void setUnitsOfMeasureId(int unitsOfMeasureId) {
    this.unitsOfMeasureId = unitsOfMeasureId;
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

  public Long getProductId() {
    return productId;
  }

  public int getUnitsOfMeasureId() {
    return unitsOfMeasureId;
  }

  public int getConversionFactor() {
    return conversionFactor;
  }

  public BigDecimal getPrice() {
    return price;
  }
}
