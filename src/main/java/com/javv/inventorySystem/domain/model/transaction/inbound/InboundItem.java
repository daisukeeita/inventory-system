package com.javv.inventorySystem.domain.model.transaction.inbound;

public class InboundItem {

  private Long id;
  private Long productId;
  private int packagingId;
  private int quantityReceived;
  private int baseQuantityEquivalent;

  public InboundItem() {
  }

  public InboundItem(
      Long productId,
      int packagingId,
      int quantityReceived,
      int baseQuantityEquivalent) {
    this.productId = productId;
    this.packagingId = packagingId;
    this.quantityReceived = quantityReceived;
    this.baseQuantityEquivalent = baseQuantityEquivalent;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public void setPackagingCode(int packagingId) {
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

  public Long getProductId() {
    return productId;
  }

  public int getPackagingId() {
    return packagingId;
  }

  public int getQuantityReceived() {
    return quantityReceived;
  }

  public int getBaseQuantityEquivalent() {
    return baseQuantityEquivalent;
  }
}
