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
@Table(name = "personal_details")
public class PersonalDetailsJpaEntity {
  @Id
  private UUID id;

  @OneToOne
  @MapsId
  @JoinColumn(name = "user_id")
  private UserJpaEntity userJpaEntity;

  @Column(name = "display_name", length = 255, nullable = false)
  private String displayName;

  @Column(name = "first_name", length = 100, nullable = false)
  private String firstName;

  @Column(name = "middle_initial", length = 5, nullable = true)
  private String middleInitial;

  @Column(name = "last_name", length = 100, nullable = false)
  private String lastName;

  @Column(name = "profile_picture", length = 255, nullable = true)
  private String profilePicture;

  public PersonalDetailsJpaEntity() {
  }

  public PersonalDetailsJpaEntity(
      UserJpaEntity userJpaEntity,
      String firstName,
      String middleInitial,
      String lastName) {
    this.userJpaEntity = userJpaEntity;
    this.firstName = firstName;
    this.middleInitial = middleInitial;
    this.lastName = lastName;
  }

  public void setUser(UserJpaEntity userJpaEntity) {
    this.userJpaEntity = userJpaEntity;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setMiddleInitial(String middleInitial) {
    this.middleInitial = middleInitial;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setProfilePicture(String profilePicture) {
    this.profilePicture = profilePicture;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public UUID getUserId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getMiddleInitial() {
    return middleInitial;
  }

  public String getLastName() {
    return lastName;
  }

  public String getDisplayName() {
    return displayName;
  }

  public String getProfilePicture() {
    return profilePicture;
  }
}
