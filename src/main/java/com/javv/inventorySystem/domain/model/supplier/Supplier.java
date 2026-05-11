package com.javv.inventorySystem.domain.model.supplier;

public class Supplier {
  private int id;
  private String companyName;
  private String contactName;
  private String phoneNumber;
  private String email;

  public Supplier() {}

  public Supplier(String companyName, String contactName, String phoneNumber, String email) {
    this.companyName = companyName;
    this.contactName = contactName;
    this.phoneNumber = phoneNumber;
    this.email = email;
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
}
