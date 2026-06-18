package com.javv.inventorySystem.domain.model.supplier;

import java.time.Instant;

public class Supplier {
  private Integer id;
  private String supplierCode;
  private String companyName;
  private String contactName;
  private String phoneNumber;
  private String email;
  private SupplierAddress supplierAddress;
  private Instant createdAt;
  private Instant updatedAt;

  public Supplier() {
  }

  public Supplier(
      String supplierCode,
      String companyName,
      String contactName,
      String phoneNumber,
      String email,
      SupplierAddress supplierAddress,
      Instant createdAt,
      Instant updatedAt) {
    this.supplierCode = supplierCode;
    this.companyName = companyName;
    this.contactName = contactName;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.supplierAddress = supplierAddress;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setSupplierCode(String supplierCode) {
    this.supplierCode = supplierCode;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public void setContactName(String contactName) {
    this.contactName = contactName;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setSupplierAddress(SupplierAddress supplierAddress) {
    this.supplierAddress = supplierAddress;
  }

  public void setCreatedAt(Instant createdAt) {
    this.createdAt = createdAt;
  }

  public void setUpdatedAt(Instant updatedAt) {
    this.updatedAt = updatedAt;
  }

  public Integer getId() {
    return id;
  }

  public String getSupplierCode() {
    return supplierCode;
  }

  public String getCompanyName() {
    return companyName;
  }

  public String getContactName() {
    return contactName;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public String getEmail() {
    return email;
  }

  public SupplierAddress getSupplierAddress() {
    return supplierAddress;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }

  public void updateAddress(
      String street, String city, String state, String postalCode, String country) {
    this.supplierAddress = new SupplierAddress(this.id, street, city, state, postalCode, country);
  }
}
