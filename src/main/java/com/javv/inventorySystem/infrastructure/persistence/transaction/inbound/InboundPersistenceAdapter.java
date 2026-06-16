package com.javv.inventorySystem.infrastructure.persistence.transaction.inbound;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.javv.inventorySystem.domain.model.transaction.inbound.Inbound;
import com.javv.inventorySystem.domain.model.transaction.inbound.InboundItem;
import com.javv.inventorySystem.domain.repository.InboundRepositoryInterface;
import com.javv.inventorySystem.infrastructure.persistence.product.ProductJpaEntity;
import com.javv.inventorySystem.infrastructure.persistence.product.ProductJpaRepository;
import com.javv.inventorySystem.infrastructure.persistence.productPackaging.ProductPackagingJpaEntity;
import com.javv.inventorySystem.infrastructure.persistence.productPackaging.ProductPackagingJpaRepository;
import com.javv.inventorySystem.infrastructure.persistence.supplier.SupplierJpaEntity;
import com.javv.inventorySystem.infrastructure.persistence.supplier.SupplierJpaRepository;
import com.javv.inventorySystem.infrastructure.persistence.user.UserJpaEntity;
import com.javv.inventorySystem.infrastructure.persistence.user.UserJpaRepository;

@Repository
public class InboundPersistenceAdapter implements InboundRepositoryInterface {

  @Autowired
  private InboundJpaRepository inboundJpaRepository;

  @Autowired
  private UserJpaRepository userJpaRepository;

  @Autowired
  private ProductJpaRepository productJpaRepository;

  @Autowired
  private SupplierJpaRepository supplierJpaRepository;

  @Autowired
  private ProductPackagingJpaRepository productPackagingJpaRepository;

  @Override
  public Inbound save(Inbound inbound) {

    UserJpaEntity userJpaEntity = userJpaRepository.getReferenceById(
        inbound.getEncoderId());

    SupplierJpaEntity supplierJpaEntity = supplierJpaRepository.getReferenceById(
        inbound.getSupplierId());

    InboundJpaEntity inboundJpaEntity = new InboundJpaEntity();
    inboundJpaEntity.setSupplierJpaEntity(supplierJpaEntity);
    inboundJpaEntity.setUserJpaEntity(userJpaEntity);
    inboundJpaEntity.setInvoiceNumber(
        inbound.getInvoiceNumber());
    inboundJpaEntity.setDateReceived(
        inbound.getDateReceived());

    for (InboundItem item : inbound.getListInboundItem()) {

      ProductJpaEntity productJpaEntity = productJpaRepository
          .getReferenceBySku(item.getProductSku());

      ProductPackagingJpaEntity productPackagingJpaEntity = productPackagingJpaRepository
          .getReferenceById(item.getPackagingId());

      inboundJpaEntity.addItem(
          productJpaEntity,
          productPackagingJpaEntity,
          item.getQuantityReceived(),
          item.getBaseQuantityEquivalent());
    }

    InboundJpaEntity savedEntity = inboundJpaRepository.saveAndFlush(inboundJpaEntity);

    Inbound inboundDomain = new Inbound();
    inboundDomain.setId(savedEntity.getId());
    inboundDomain.setEncoderId(savedEntity.getUserJpaEntity().getUserId());
    inboundDomain.setSupplierId(savedEntity.getSupplierJpaEntity().getId());
    inboundDomain.setInvoiceNumber(savedEntity.getInvoiceNumber());
    inboundDomain.setDateReceived(savedEntity.getDateReceived());
    inboundDomain.setCreatedAt(savedEntity.getCreatedAt());
    inboundDomain.setUpdatedAt(savedEntity.getUpdatedAt());

    for (InboundItemJpaEntity itemJpa : savedEntity.getListInboundItem()) {
      inboundDomain.addInboundItem(
          itemJpa.getProduct().getSku(),
          itemJpa.getProductPackagingJpaEntity().getId(),
          itemJpa.getQuantityReceived(),
          itemJpa.getBaseQuantityEquivalent());
    }

    return inboundDomain;
  }

  @Override
  public Inbound update(Inbound inbound) {
    return null;
  }

  @Override
  public Optional<Inbound> findById(Long id) {
    Optional<InboundJpaEntity> optionalEntity = inboundJpaRepository.findById(id);
    return null;
  }

  @Override
  public Page<Inbound> findAll(Pageable pageable) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Inbound> findByDateReceived(LocalDateTime dateReceived) {
    // TODO Auto-generated method stub
    return null;
  }
}
