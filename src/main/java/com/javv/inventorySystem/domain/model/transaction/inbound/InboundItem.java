package com.javv.inventorySystem.domain.model.transaction.inbound;

public class InboundItem {

  private Long id;
  private Long inboundId;
  private String productSku;
  private Long packagingId;
  private int quantityReceived;
  private int baseQuantityEquivalent;

  public InboundItem() {
  }

  public InboundItem(
      Long inboundId,
      String productSku,
      Long packagingId,
      int quantityReceived,
      int baseQuantityEquivalent) {
    this.inboundId = inboundId;
    this.productSku = productSku;
    this.packagingId = packagingId;
    this.quantityReceived = quantityReceived;
    this.baseQuantityEquivalent = baseQuantityEquivalent;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setInboundId(Long inboundId) {
    this.inboundId = inboundId;
  }

  public void setProductSku(String productSku) {
    this.productSku = productSku;
  }

  public void setPackagingId(Long packagingId) {
    this.packagingId = packagingId;
  }

  public void setQuantityReceived(int quantityReceived) {
    this.quantityReceived = quantityReceived;
  }

  public void setBasedQuantityEquivalent(int baseQuantityEquivalent) {
    this.baseQuantityEquivalent = baseQuantityEquivalent;
  }

  public Long getId() {
    return id;
  }

  public Long getInboundId() {
    return inboundId;
  }

  public String getProductSku() {
    return productSku;
  }

  public Long getPackagingId() {
    return packagingId;
  }

  public int getQuantityReceived() {
    return quantityReceived;
  }

  public int getBaseQuantityEquivalent() {
    return baseQuantityEquivalent;
  }
}
