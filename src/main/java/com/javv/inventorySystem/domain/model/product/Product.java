package com.javv.inventorySystem.domain.model.product;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Product {
  private Long id;
  private String sku;
  private String name;
  private String supplierCode;
  private String baseUomName;
  private List<ProductPackaging> listPackages = new ArrayList<ProductPackaging>();
  private Instant createdAt;
  private Instant updatedAt;

  public Product() {
  }

  public Product(
      Long id,
      String sku,
      String name,
      String supplierCode,
      String baseUomName,
      List<ProductPackaging> listPackages) {
    this.id = id;
    this.sku = sku;
    this.name = name;
    this.supplierCode = supplierCode;
    this.baseUomName = baseUomName;
    this.listPackages = listPackages;
  }

  public ProductPackaging addPackaging(
      String packagingCode,
      String unitsOfMeasureName,
      int conversionFactor,
      BigDecimal price) {

    ProductPackaging productPackaging = new ProductPackaging();
    productPackaging.setPackagingCode(packagingCode);
    productPackaging.setUnitsOfMeasureName(unitsOfMeasureName);
    productPackaging.setConversionFactor(conversionFactor);
    productPackaging.setPrice(price);
    productPackaging.setProductSku(this.getSku());

    this.listPackages.add(productPackaging);

    return productPackaging;
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

  public void setSupplierCode(String supplierCode) {
    this.supplierCode = supplierCode;
  }

  public void setBaseUnitsOfMeasureName(String baseUomName) {
    this.baseUomName = baseUomName;
  }

  public void setListPackages(List<ProductPackaging> listPackages) {
    this.listPackages = listPackages;
  }

  public void setCreatedAt(Instant createdAt) {
    this.createdAt = createdAt;
  }

  public void setUpdatedAt(Instant updatedAt) {
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

  public String getSupplierCode() {
    return supplierCode;
  }

  public String getBaseUnitsOfMeasureName() {
    return baseUomName;
  }

  public List<ProductPackaging> getListPackages() {
    return listPackages;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }
}
