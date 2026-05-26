package com.javv.inventorySystem.domain.model.transaction.inbound;

import com.javv.inventorySystem.domain.model.product.Product;
import com.javv.inventorySystem.domain.model.product.ProductPackaging;

public class InboundItem {

  private Long inboundId;
  private Product product;
  private ProductPackaging packagingType;
  private int quantityReceived;
  private int baseQuantityEquivalent;

  public InboundItem() {
  }

  public InboundItem(
      Long inboundId,
      Product product,
      ProductPackaging packagingType,
      int quantityReceived,
      int baseQuantityEquivalent) {
    this.inboundId = inboundId;
    this.packagingType = packagingType;
    this.quantityReceived = quantityReceived;
    this.baseQuantityEquivalent = baseQuantityEquivalent;
  }

  public void setId(Long inboundId) {
    this.inboundId = inboundId;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public void setPackagingType(ProductPackaging productPackaging) {
    this.packagingType = packagingType;
  }

  public void setQuantityReceived(int quantityReceived) {
    this.quantityReceived = quantityReceived;
  }

  public void setBasedQuantityEquivalent(int baseQuantityEquivalent) {
    this.baseQuantityEquivalent = baseQuantityEquivalent;
  }

  public Long getInboundId() {
    return inboundId;
  }

  public Product getProduct() {
    return product;
  }

  public ProductPackaging getPackagingType() {
    return packagingType;
  }

  public int getQuantityReceived() {
    return quantityReceived;
  }

  public int getBaseQuantityEquivalent() {
    return baseQuantityEquivalent;
  }
}
