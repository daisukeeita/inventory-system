package com.javv.inventorySystem.infrastructure.persistence.user;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "contact_infomration")
public class ContactInformationJpaEntity {
  @Id
  private UUID id;

  @OneToOne
  @MapsId
  @JoinColumn(name = "user_id")
  private UserJpaEntity user;

  @Column(name = "email", length = 155, unique = true, nullable = false)
  private String email;

  @Column(name = "phone_number", length = 20, nullable = false)
  private String phoneNumber;

  @Column(name = "mailing_address", length = 255, nullable = true)
  private String mailingAddress;

  public ContactInformationJpaEntity() {
  }

  public ContactInformationJpaEntity(
      UserJpaEntity user,
      String email,
      String phoneNumber,
      String mailingAddress) {
    this.user = user;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.mailingAddress = mailingAddress;
  }

  public void setUser(UserJpaEntity user) {
    this.user = user;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public void setMailingAddress(String mailingAddress) {
    this.mailingAddress = mailingAddress;
  }

  public UUID getUserId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public String getMailingAddress() {
    return mailingAddress;
  }
}
