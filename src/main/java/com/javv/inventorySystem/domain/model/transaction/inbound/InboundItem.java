package com.javv.inventorySystem.domain.model.transaction.inbound;

public class InboundItem {

  private Long id;
  private String productSku;
  private String packagingCode;
  private int quantityReceived;
  private int baseQuantityEquivalent;

  public InboundItem() {
  }

  public InboundItem(
      String productSku,
      String packagingCode,
      int quantityReceived,
      int baseQuantityEquivalent) {
    this.productSku = productSku;
    this.packagingCode = packagingCode;
    this.quantityReceived = quantityReceived;
    this.baseQuantityEquivalent = baseQuantityEquivalent;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setProductSku(String productSku) {
    this.productSku = productSku;
  }

  public void setPackagingCode(String packagingCode) {
    this.packagingCode = packagingCode;
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

  public String getProductSku() {
    return productSku;
  }

  public String getPackagingCode() {
    return packagingCode;
  }

  public int getQuantityReceived() {
    return quantityReceived;
  }

  public int getBaseQuantityEquivalent() {
    return baseQuantityEquivalent;
  }
}
