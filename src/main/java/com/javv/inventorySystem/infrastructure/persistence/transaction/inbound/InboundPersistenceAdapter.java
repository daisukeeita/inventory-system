package com.javv.inventorySystem.infrastructure.persistence.transaction.inbound;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.javv.inventorySystem.domain.model.transaction.inbound.Inbound;
import com.javv.inventorySystem.domain.model.transaction.inbound.InboundItem;
import com.javv.inventorySystem.domain.repository.InboundRepositoryInterface;
import com.javv.inventorySystem.infrastructure.persistence.product.ProductJpaEntity;
import com.javv.inventorySystem.infrastructure.persistence.product.ProductPersistenceAdapter;
import com.javv.inventorySystem.infrastructure.persistence.productPackaging.ProductPackagingJpaEntity;
import com.javv.inventorySystem.infrastructure.persistence.productPackaging.ProductPackagingPersistenceAdapter;
import com.javv.inventorySystem.infrastructure.persistence.supplier.SupplierJpaEntity;
import com.javv.inventorySystem.infrastructure.persistence.supplier.SupplierPersistenceAdapter;
import com.javv.inventorySystem.infrastructure.persistence.user.UserJpaEntity;
import com.javv.inventorySystem.infrastructure.persistence.user.UserPersistenceAdapter;

@Repository
public class InboundPersistenceAdapter implements InboundRepositoryInterface {

  private InboundJpaRepository inboundJpaRepository;
  private UserPersistenceAdapter userPersistenceAdapter;
  private ProductPersistenceAdapter productPersistenceAdapter;
  private SupplierPersistenceAdapter supplierPersistenceAdapter;
  private ProductPackagingPersistenceAdapter productPackagingPersistenceAdapter;

  public InboundPersistenceAdapter(
      InboundJpaRepository inboundJpaRepository,
      UserPersistenceAdapter userPersistenceAdapter,
      ProductPersistenceAdapter productPersistenceAdapter,
      SupplierPersistenceAdapter supplierPersistenceAdapter,
      ProductPackagingPersistenceAdapter productPackagingPersistenceAdapter) {
    this.inboundJpaRepository = inboundJpaRepository;
    this.userPersistenceAdapter = userPersistenceAdapter;
    this.productPersistenceAdapter = productPersistenceAdapter;
    this.supplierPersistenceAdapter = supplierPersistenceAdapter;
    this.productPackagingPersistenceAdapter = productPackagingPersistenceAdapter;
  }

  @Override
  public Inbound save(Inbound inbound) {

    UserJpaEntity userJpaEntity = userPersistenceAdapter.getReferenceById(
        inbound.getEncoderId());

    SupplierJpaEntity supplierJpaEntity = supplierPersistenceAdapter.getReferenceById(
        inbound.getSupplierId());

    InboundJpaEntity inboundJpaEntity = new InboundJpaEntity();
    inboundJpaEntity.setSupplierJpaEntity(supplierJpaEntity);
    inboundJpaEntity.setUserJpaEntity(userJpaEntity);
    inboundJpaEntity.setInvoiceNumber(
        inbound.getInvoiceNumber());
    inboundJpaEntity.setDateReceived(
        inbound.getDateReceived());

    for (InboundItem item : inbound.getListInboundItem()) {

      ProductJpaEntity productJpaEntity = productPersistenceAdapter
          .getReferenceById(item.getProductId());

      ProductPackagingJpaEntity productPackagingJpaEntity = productPackagingPersistenceAdapter
          .getReferenceById(item.getPackagingId());

      inboundJpaEntity.addItem(
          productJpaEntity,
          productPackagingJpaEntity,
          item.getQuantityReceived(),
          item.getBaseQuantityEquivalent());
    }

    InboundJpaEntity savedEntity = inboundJpaRepository.save(inboundJpaEntity);

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
          itemJpa.getProduct().getId(),
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
  public Optional<Inbound> getById(Long id) {
    Optional<InboundJpaEntity> optionalEntity = inboundJpaRepository.findById(id);
    return null;
  }

  @Override
  public List<Inbound> getAll() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Inbound> getListByDate() {
    // TODO Auto-generated method stub
    return null;
  }
}
