package com.javv.inventorySystem.domain.model.supplier;

public class SupplierAddress {
  private int supplierId;
  private String street;
  private String city;
  private String state;
  private String postalCode;
  private String country;

  public SupplierAddress() {
  }

  public SupplierAddress(
      int supplierId, String street, String city, String state, String postalCode, String country) {
    this.supplierId = supplierId;
    this.street = street;
    this.city = city;
    this.state = state;
    this.postalCode = postalCode;
    this.country = country;
  }

  public void setSupplierId(int supplierId) {
    this.supplierId = supplierId;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public void setState(String state) {
    this.state = state;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public int getSupplierId() {
    return supplierId;
  }

  public String getStreet() {
    return street;
  }

  public String getCity() {
    return city;
  }

  public String getState() {
    return state;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public String getCountry() {
    return country;
  }

  public String getFullAddress() {
    return street + ", " + city + ", " + state + ", " + postalCode + ", " + country;
  }
}
