package com.javv.inventorySystem.infrastructure.persistence.supplier;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "supplier_address")
public class SupplierAddressJpaEntity {

  @Id private int id;

  @OneToOne
  @MapsId
  @JoinColumn(name = "supplier_id")
  private SupplierJpaEntity supplierJpaEntity;

  @Column(name = "street", length = 100, nullable = false)
  private String street;

  @Column(name = "city", length = 100, nullable = false)
  private String city;

  @Column(name = "state", length = 100, nullable = false)
  private String state;

  @Column(name = "postal_code", length = 100, nullable = false)
  private String postalCode;

  @Column(name = "country", length = 100, nullable = false)
  private String country;

  public SupplierAddressJpaEntity() {}

  public SupplierAddressJpaEntity(
      String street, String city, String state, String postalCode, String country) {
    this.street = street;
    this.city = city;
    this.state = state;
    this.postalCode = postalCode;
    this.country = country;
  }

  public void setSupplier(SupplierJpaEntity supplierJpaEntity) {
    this.supplierJpaEntity = supplierJpaEntity;
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
    return id;
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
}
