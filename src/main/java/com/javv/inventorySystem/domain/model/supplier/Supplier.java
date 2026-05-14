package com.javv.inventorySystem.domain.model.supplier;

public class Supplier {
  private int id;
  private String companyName;
  private String contactName;
  private String phoneNumber;
  private String email;
  private SupplierAddress supplierAddress;

  public Supplier() {
  }

  public Supplier(String companyName, String contactName, String phoneNumber, String email,
      SupplierAddress supplierAddress) {
    this.companyName = companyName;
    this.contactName = contactName;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.supplierAddress = supplierAddress;
  }

  public void setId(int id) {
    this.id = id;
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

  public int getId() {
    return id;
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
}
