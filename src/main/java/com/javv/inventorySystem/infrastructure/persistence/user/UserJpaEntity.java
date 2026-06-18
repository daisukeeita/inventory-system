package com.javv.inventorySystem.infrastructure.persistence.user;

import java.time.LocalDate;
import java.util.UUID;

import com.javv.inventorySystem.infrastructure.persistence.role.RoleJpaEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users", indexes = {
    @Index(name = "idx_users_username", columnList = "username")
})
public class UserJpaEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "username", nullable = false, unique = true, length = 50)
  private String username;

  @Column(name = "hashed_password", nullable = false, length = 255)
  private String hashedPassword;

  @Column(name = "is_active", nullable = false)
  private boolean isActive = true;

  @Column(name = "status", nullable = false)
  private String status;

  @ManyToOne
  @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
  private RoleJpaEntity role;

  @OneToOne(mappedBy = "userJpaEntity", cascade = CascadeType.ALL, orphanRemoval = true)
  private PersonalDetailsJpaEntity personalDetailsJpaEntity;

  @OneToOne(mappedBy = "userJpaEntity", cascade = CascadeType.ALL, orphanRemoval = true)
  private ContactInformationJpaEntity contactInformationJpaEntity;

  @Column(name = "created_at", nullable = false)
  private LocalDate createdAt = LocalDate.now();

  @Column(name = "updated_at", nullable = false)
  private LocalDate updatedAt = LocalDate.now();

  public UserJpaEntity() {
  }

  public UserJpaEntity(
      String username,
      String hashedPassword,
      String status,
      RoleJpaEntity role,
      PersonalDetailsJpaEntity personalDetailsJpaEntity,
      ContactInformationJpaEntity contactInformationJpaEntity) {
    this.username = username;
    this.hashedPassword = hashedPassword;
    this.status = status;
    this.role = role;
    this.personalDetailsJpaEntity = personalDetailsJpaEntity;
    this.contactInformationJpaEntity = contactInformationJpaEntity;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setHashedPassword(String hashedPassword) {
    this.hashedPassword = hashedPassword;
  }

  public void setUpdatedAt(LocalDate localDate) {
    this.updatedAt = localDate;
  }

  public void setRole(RoleJpaEntity role) {
    this.role = role;
  }

  public void setUserStatus(String status) {
    this.status = status;
  }

  public void setPersonalDetails(PersonalDetailsJpaEntity personalDetailsJpaEntity) {
    this.personalDetailsJpaEntity = personalDetailsJpaEntity;
  }

  public void setContactInformation(ContactInformationJpaEntity contactInformationJpaEntity) {
    this.contactInformationJpaEntity = contactInformationJpaEntity;
  }

  public UUID getUserId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getHashedPassword() {
    return hashedPassword;
  }

  public String getUserStatus() {
    return status;
  }

  public RoleJpaEntity getRole() {
    return role;
  }

  public LocalDate getCreatedAt() {
    return createdAt;
  }

  public LocalDate getUpdatedAt() {
    return updatedAt;
  }

  public boolean getIsActive() {
    return isActive;
  }

  public PersonalDetailsJpaEntity getPersonalDetailsJpaEntity() {
    return personalDetailsJpaEntity;
  }

  public ContactInformationJpaEntity getContactInformationJpaEntity() {
    return contactInformationJpaEntity;
  }
}
