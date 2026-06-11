package com.javv.inventorySystem.infrastructure.persistence.supplier;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "supplier")
@EntityListeners(AuditingEntityListener.class)
public class SupplierJpaEntity {

  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "company_name", nullable = false, length = 255, unique = true)
  private String companyName;

  @Column(name = "contact_name", nullable = false, length = 155)
  private String contactName;

  @Column(name = "phone_number", nullable = false, length = 15)
  private String phoneNumber;

  @Column(name = "email", nullable = false, length = 155, unique = true)
  private String email;

  @OneToOne(mappedBy = "supplierJpaEntity", cascade = CascadeType.ALL, orphanRemoval = true)
  private SupplierAddressJpaEntity supplierAddressJpaEntity;

  @CreatedDate
  @Column(name = "created_at", nullable = false, updatable = false)
  private Instant createdAt;

  @LastModifiedDate
  @Column(name = "updated_at", nullable = false)
  private Instant updatedAt;

  public SupplierJpaEntity() {
  }

  public SupplierJpaEntity(
      String companyName,
      String contactName,
      String phoneNumber,
      String email,
      SupplierAddressJpaEntity supplierAddressJpaEntity) {
    this.companyName = companyName;
    this.contactName = contactName;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.supplierAddressJpaEntity = supplierAddressJpaEntity;
  }

  public void setId(Integer id) {
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

  public void setSupplierAddress(SupplierAddressJpaEntity supplierAddressJpaEntity) {
    this.supplierAddressJpaEntity = supplierAddressJpaEntity;
  }

  public Integer getId() {
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

  public SupplierAddressJpaEntity getSupplierAddressJpaEntity() {
    return supplierAddressJpaEntity;
  }

  public void updateAddress(
      String street, String city, String state, String postalCode, String country) {
    if (this.supplierAddressJpaEntity == null) {
      this.supplierAddressJpaEntity = new SupplierAddressJpaEntity();
      this.supplierAddressJpaEntity.setSupplier(this);
    }
    this.supplierAddressJpaEntity.setSupplier(this);
    this.supplierAddressJpaEntity.setStreet(street);
    this.supplierAddressJpaEntity.setCity(city);
    this.supplierAddressJpaEntity.setState(state);
    this.supplierAddressJpaEntity.setPostalCode(postalCode);
    this.supplierAddressJpaEntity.setCountry(country);
  }
}
