package com.javv.inventorySystem.infrastructure.persistence.transaction.inbound;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

import com.javv.inventorySystem.infrastructure.persistence.user.UserJpaEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "inbound")
public class InboundJpaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToMany
  @JoinColumn(name = "username", referencedColumnName = "username")
  private UserJpaEntity encoder;

  @Column(name = "invoice_number")
  private String invoiceNumber;

  @Column(name = "date_received")
  private LocalDateTime dateReceived;

  @Column(name = "list_inbound_item")
  private List<InboundItemJpaEntity> listInboundItem;

  @Column(name = "created_at", updatable = false)
  private Instant createdAt;

  @Column(name = "updated_at")
  private Instant updatedAt;

  public InboundJpaEntity() {
  }

  public InboundJpaEntity(
      Long id,
      UserJpaEntity encoder,
      String invoiceNumber,
      LocalDateTime dateReceived,
      List<InboundItemJpaEntity> listInboundItem) {
    this.id = id;
    this.encoder = encoder;
    this.dateReceived = dateReceived;
    this.listInboundItem = listInboundItem;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setEncoder(UserJpaEntity encoder) {
    this.encoder = encoder;
  }

  public void setInvoiceNumber(String invoiceNumber) {
    this.invoiceNumber = invoiceNumber;
  }

  public void setDateReceived(LocalDateTime dateReceived) {
    this.dateReceived = dateReceived;
  }

  public void setListInboundItem(List<InboundItemJpaEntity> listInboundItem) {
    this.listInboundItem = listInboundItem;
  }

  protected void setCreatedAt(Instant createdAt) {
    this.createdAt = createdAt;
  }

  protected void setUpdatedAt(Instant updatedAt) {
    this.updatedAt = updatedAt;
  }

  public Long getId() {
    return id;
  }

  public UserJpaEntity getEncoder() {
    return encoder;
  }

  public String getInvoiceNumber() {
    return invoiceNumber;
  }

  public LocalDateTime getDateReceived() {
    return dateReceived;
  }

  public List<InboundItemJpaEntity> getListInboundItem() {
    return listInboundItem;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }
}
