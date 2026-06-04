package com.javv.inventorySystem.domain.model.transaction.inbound;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Inbound {

  private Long id;
  private Integer supplierId;
  private UUID encoderId;
  private String invoiceNumber;
  private LocalDateTime dateReceived;
  private List<InboundItem> listInboundItem = new ArrayList<>();
  private Instant createdAt;
  private Instant updatedAt;

  public Inbound() {
  }

  public Inbound(
      Long id,
      Integer supplierId,
      UUID encoderId,
      String invoiceNumber,
      LocalDateTime dateReceived,
      List<InboundItem> listInboundItem) {
    this.id = id;
    this.supplierId = supplierId;
    this.encoderId = encoderId;
    this.invoiceNumber = invoiceNumber;
    this.dateReceived = dateReceived;
    this.listInboundItem = listInboundItem;
  }

  public InboundItem addInboundItem(
      String productSku,
      int packagingId,
      int quantityReceived,
      int baseQuantityEquivalent) {

    InboundItem item = new InboundItem();
    item.setProductSku(productSku);
    item.setPackagingCode(packagingId);
    item.setQuantityReceived(quantityReceived);
    item.setBasedQuantityEquivalent(baseQuantityEquivalent);

    this.listInboundItem.add(item);

    return item;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setSupplierName(Integer supplierId) {
    this.supplierId = supplierId;
  }

  public void setEncoderUsername(UUID encoderId) {
    this.encoderId = encoderId;
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

  public Integer getSupplierId() {
    return supplierId;
  }

  public UUID getEncoderId() {
    return encoderId;
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
