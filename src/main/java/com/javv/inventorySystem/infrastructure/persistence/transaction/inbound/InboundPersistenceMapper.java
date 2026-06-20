package com.javv.inventorySystem.infrastructure.persistence.transaction.inbound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javv.inventorySystem.domain.model.transaction.inbound.Inbound;
import com.javv.inventorySystem.domain.model.transaction.inbound.InboundItem;
import com.javv.inventorySystem.infrastructure.persistence.product.ProductJpaEntity;
import com.javv.inventorySystem.infrastructure.persistence.product.ProductJpaRepository;
import com.javv.inventorySystem.infrastructure.persistence.productPackaging.ProductPackagingJpaEntity;
import com.javv.inventorySystem.infrastructure.persistence.productPackaging.ProductPackagingJpaRepository;
import com.javv.inventorySystem.infrastructure.persistence.supplier.SupplierJpaRepository;
import com.javv.inventorySystem.infrastructure.persistence.user.UserJpaRepository;

@Component
public class InboundPersistenceMapper {

  @Autowired
  private UserJpaRepository userJpaRepository;

  @Autowired
  private SupplierJpaRepository supplierJpaRepository;

  @Autowired
  private ProductJpaRepository productJpaRepository;

  @Autowired
  private ProductPackagingJpaRepository productPackagingJpaRepository;

  public InboundJpaEntity toJpaEntity(Inbound inbound) {

    // UserJpaEntity userJpaEntity = userJpaRepository.getReferenceById(
    // inbound.getEncoderId());

    // SupplierJpaEntity supplierJpaEntity = supplierJpaRepository.getReferenceById(
    // inbound.getSupplierId());

    InboundJpaEntity inboundJpaEntity = new InboundJpaEntity();
    // inboundJpaEntity.setSupplierJpaEntity(supplierJpaEntity);
    // inboundJpaEntity.setUserJpaEntity(userJpaEntity);
    inboundJpaEntity.setInvoiceNumber(inbound.getInvoiceNumber());
    inboundJpaEntity.setDateReceived(inbound.getDateReceived());

    for (InboundItem inboundItem : inbound.getListInboundItem()) {

      ProductJpaEntity productJpaEntity = productJpaRepository
          .getReferenceBySku(inboundItem.getProductSku());

      ProductPackagingJpaEntity productPackagingJpaEntity = productPackagingJpaRepository
          .getReferenceById(inboundItem.getPackagingCode());

      inboundJpaEntity.addItem(
          productJpaEntity,
          productPackagingJpaEntity,
          inboundItem.getQuantityReceived(),
          inboundItem.getBaseQuantityEquivalent());
    }

    return inboundJpaEntity;
  }

  public Inbound toDomainEntity(InboundJpaEntity inboundJpaEntity) {

    Inbound inbound = new Inbound();

    inbound.setId(inboundJpaEntity.getId());
    // inbound.setSupplierId(inboundJpaEntity.getSupplierJpaEntity().getId());
    // inbound.setEncoderId(inboundJpaEntity.getUserJpaEntity().getUserId());
    inbound.setInvoiceNumber(inboundJpaEntity.getInvoiceNumber());
    inbound.setDateReceived(inboundJpaEntity.getDateReceived());
    inbound.setCreatedAt(inboundJpaEntity.getCreatedAt());
    inbound.setUpdatedAt(inboundJpaEntity.getUpdatedAt());

    for (InboundItemJpaEntity inboundItemJpaEntity : inboundJpaEntity.getListInboundItem()) {

      InboundItem inboundItem = inbound.addInboundItem(
          inboundItemJpaEntity.getProduct().getSku(),
          inboundItemJpaEntity.getProductPackagingJpaEntity().getId(),
          inboundItemJpaEntity.getQuantityReceived(),
          inboundItemJpaEntity.getBaseQuantityEquivalent());

      inboundItem.setId(inboundItemJpaEntity.getId());
    }

    return inbound;
  }

  public InboundJpaEntity updateJpaEntity(Inbound inbound, InboundJpaEntity inboundJpaEntity) {
    return null;
  }

}
