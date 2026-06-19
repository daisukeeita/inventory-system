package com.javv.inventorySystem.domain.model.product;

import java.math.BigDecimal;

public class ProductPackaging {
  private Long id;
  private String packagingCode;
  private String productSku;
  private String unitsOfMeasureName;
  private int conversionFactor;
  private BigDecimal price;

  public ProductPackaging() {
  }

  public ProductPackaging(
      Long id,
      String packagingCode,
      String productSku,
      String unitsOfMeasureName,
      int conversionFactor,
      BigDecimal price) {
    this.id = id;
    this.packagingCode = packagingCode;
    this.productSku = productSku;
    this.unitsOfMeasureName = unitsOfMeasureName;
    this.conversionFactor = conversionFactor;
    this.price = price;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setPackagingCode(String packagingCode) {
    this.packagingCode = packagingCode;
  }

  public void setProductSku(String productSku) {
    this.productSku = productSku;
  }

  public void setUnitsOfMeasureName(String unitsOfMeasureName) {
    this.unitsOfMeasureName = unitsOfMeasureName;
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

  public String getProductSku() {
    return productSku;
  }

  public String getUnitsOfMeasureName() {
    return unitsOfMeasureName;
  }

  public int getConversionFactor() {
    return conversionFactor;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public int calculateBaseQuantity(int receivedPackages) {
    return receivedPackages * this.conversionFactor;
  }
}
