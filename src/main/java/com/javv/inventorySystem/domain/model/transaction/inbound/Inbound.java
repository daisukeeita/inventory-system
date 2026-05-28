package com.javv.inventorySystem.domain.model.transaction.inbound;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.javv.inventorySystem.domain.model.supplier.Supplier;
import com.javv.inventorySystem.domain.model.user.User;

public class Inbound {

  private Long id;
  private Supplier supplier;
  private User encoder;
  private String invoiceNumber;
  private LocalDateTime dateReceived;
  private List<InboundItem> listInboundItem = new ArrayList<>();
  private Instant createdAt;
  private Instant updatedAt;

  public Inbound() {}

  public Inbound(
      Long id,
      Supplier supplier,
      User encoder,
      String invoiceNumber,
      LocalDateTime dateReceived,
      List<InboundItem> listInboundItem) {
    this.id = id;
    this.supplier = supplier;
    this.encoder = encoder;
    this.invoiceNumber = invoiceNumber;
    this.dateReceived = dateReceived;
    this.listInboundItem = listInboundItem;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setSupplier(Supplier supplier) {
    this.supplier = supplier;
  }

  public void setEncoder(User encoder) {
    this.encoder = encoder;
  }

  public void setInvoiceNumber(String invoiceNumber) {
    this.invoiceNumber = invoiceNumber;
  }

  public void setDateReceived(LocalDateTime dateReceived) {
    this.dateReceived = dateReceived;
  }

  public void setListInboundItem(List<InboundItem> listInboundItem) {
    this.listInboundItem = listInboundItem;
  }

  public void setCreatedAt(Instant createdAt) {
    this.createdAt = createdAt;
  }

  public void setUpdatedAt(Instant updatedAt) {
    this.updatedAt = updatedAt;
  }

  public Long getId() {
    return id;
  }

  public Supplier getSupplier() {
    return supplier;
  }

  public User getEncoder() {
    return encoder;
  }

  public String getInvoiceNumber() {
    return invoiceNumber;
  }

  public LocalDateTime getDateReceived() {
    return dateReceived;
  }

  public List<InboundItem> getListInboundItem() {
    return listInboundItem;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }
}
