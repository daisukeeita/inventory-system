package com.javv.inventorySystem.domain.model.product;

import java.math.BigDecimal;

public class ProductPackaging {
  private Integer id;
  private String packagingCode;
  private Product product;
  private UnitsOfMeasure unitsOfMeasure;
  private int conversionFactor;
  private BigDecimal price;

  public ProductPackaging() {}

  public ProductPackaging(
      Integer id,
      String packagingCode,
      Product product,
      UnitsOfMeasure unitsOfMeasure,
      int conversionFactor,
      BigDecimal price) {
    this.id = id;
    this.packagingCode = packagingCode;
    this.product = product;
    this.unitsOfMeasure = unitsOfMeasure;
    this.conversionFactor = conversionFactor;
    this.price = price;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setPackagingCode(String packagingCode) {
    this.packagingCode = packagingCode;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public void setUnitsOfMeasure(UnitsOfMeasure unitsOfMeasure) {
    this.unitsOfMeasure = unitsOfMeasure;
  }

  public void setConversionFactor(int conversionFactor) {
    this.conversionFactor = conversionFactor;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public Integer getId() {
    return id;
  }

  public String getPackagingCode() {
    return packagingCode;
  }

  public Product getProduct() {
    return product;
  }

  public UnitsOfMeasure getUnitsOfMeasure() {
    return unitsOfMeasure;
  }

  public int getConversionFactor() {
    return conversionFactor;
  }

  public BigDecimal getPrice() {
    return price;
  }
}
