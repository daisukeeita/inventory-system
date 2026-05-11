package com.javv.inventorySystem.domain.model.product;


import com.javv.inventorySystem.domain.model.supplier.Supplier;

public class Product {
  private String sku;
  private String name;
  private Supplier supplier;
  private UnitsOfMeasure baseUom;

  public Product() {}

  public Product(String sku, String name, Supplier supplier, UnitsOfMeasure baseUom) {
    this.sku = sku;
    this.name = name;
    this.supplier = supplier;
    this.baseUom = baseUom;
  }

  public void setSku(String sku) {
    this.sku = sku;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setSupplier(Supplier supplier) {
    this.supplier = supplier;
  }

  public void setUnitsOfMeasure(UnitsOfMeasure baseUom) {
    this.baseUom = baseUom;
  }

  public String getSku() {
    return sku;
  }

  public String getName() {
    return name;
  }

  public Supplier getSupplier() {
    return supplier;
  }

  public UnitsOfMeasure getBaseUnitsOfMeasure() {
    return baseUom;
  }
}
