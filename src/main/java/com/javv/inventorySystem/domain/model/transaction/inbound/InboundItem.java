package com.javv.inventorySystem.domain.model.transaction.inbound;

public class InboundItem {

  private Long id;
  private Long inboundId;
  private Long productId;
  private Long packagingId;
  private int quantityReceived;
  private int baseQuantityEquivalent;

  public InboundItem() {
  }

  public InboundItem(
      Long inboundId,
      Long productId,
      Long packagingId,
      int quantityReceived,
      int baseQuantityEquivalent) {
    this.inboundId = inboundId;
    this.productId = productId;
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

  public void setProductId(Long productId) {
    this.productId = productId;
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

  public Long getProductId() {
    return productId;
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
