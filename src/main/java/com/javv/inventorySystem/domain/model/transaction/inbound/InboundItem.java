package com.javv.inventorySystem.domain.model.transaction.inbound;

import com.javv.inventorySystem.domain.model.product.Product;
import com.javv.inventorySystem.domain.model.product.ProductPackaging;

public class InboundItem {

  private Long id;
  private Inbound inbound;
  private Product product;
  private ProductPackaging packagingType;
  private int quantityReceived;
  private int baseQuantityEquivalent;

  public InboundItem() {}

  public InboundItem(
      Inbound inbound,
      Product product,
      ProductPackaging packagingType,
      int quantityReceived,
      int baseQuantityEquivalent) {
    this.inbound = inbound;
    this.packagingType = packagingType;
    this.quantityReceived = quantityReceived;
    this.baseQuantityEquivalent = baseQuantityEquivalent;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setInboud(Inbound inbound) {
    this.inbound = inbound;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public void setPackagingType(ProductPackaging packagingType) {
    this.packagingType = packagingType;
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

  public Inbound getInbound() {
    return inbound;
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
