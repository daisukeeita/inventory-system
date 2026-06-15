package com.javv.inventorySystem.domain.model.product;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Product {
  private Long id;
  private String sku;
  private String name;
  private int supplierId;
  private int baseUomId;
  private List<ProductPackaging> listPackages = new ArrayList<ProductPackaging>();
  private Instant createdAt;
  private Instant updatedAt;

  public Product() {
  }

  public Product(
      Long id,
      String sku,
      String name,
      int supplierId,
      int baseUomId,
      List<ProductPackaging> listPackages) {
    this.id = id;
    this.sku = sku;
    this.name = name;
    this.supplierId = supplierId;
    this.baseUomId = baseUomId;
    this.listPackages = listPackages;
  }

  public ProductPackaging addPackaging(
      String packagingCode,
      int unitsOfMeasureId,
      int conversionFactor,
      BigDecimal price) {

    ProductPackaging productPackaging = new ProductPackaging();
    productPackaging.setPackagingCode(packagingCode);
    productPackaging.setUnitsOfMeasureId(unitsOfMeasureId);
    productPackaging.setConversionFactor(conversionFactor);
    productPackaging.setPrice(price);
    productPackaging.setProductId(this.getId());

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

  public void setSupplierId(int supplierId) {
    this.supplierId = supplierId;
  }

  public void setBaseUnitsOfMeasureId(int baseUomId) {
    this.baseUomId = baseUomId;
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

  public int getSupplierId() {
    return supplierId;
  }

  public int getBaseUnitsOfMeasureId() {
    return baseUomId;
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
