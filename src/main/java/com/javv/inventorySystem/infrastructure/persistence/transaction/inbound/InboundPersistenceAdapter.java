package com.javv.inventorySystem.infrastructure.persistence.transaction.inbound;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.javv.inventorySystem.domain.exception.ResourceNotFoundException;
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

  @Autowired
  private InboundPersistenceMapper inboundPersistenceMapper;

  @Override
  public Inbound save(Inbound inbound) {

    InboundJpaEntity inboundJpaEntity = inboundPersistenceMapper.toJpaEntity(inbound);

    InboundJpaEntity savedEntity = inboundJpaRepository.saveAndFlush(inboundJpaEntity);

    return inboundPersistenceMapper.toDomainEntity(savedEntity);
  }

  @Override
  public Inbound update(Inbound inbound) {

    SupplierJpaEntity supplierJpaEntity = supplierJpaRepository
        .getReferenceById(inbound.getSupplierId());

    UserJpaEntity userJpaEntity = userJpaRepository
        .getReferenceById(inbound.getEncoderId());

    Map<Long, InboundItem> mappedInboundItems = inbound.getListInboundItem()
        .stream()
        .collect(Collectors.toMap(
            item -> item.getId(),
            item -> item));

    InboundJpaEntity inboundJpaEntity = inboundJpaRepository
        .findById(inbound.getId())
        .orElseThrow(() -> new ResourceNotFoundException(
            "Inbound Persistence: Inbound does not exist with ID: " + inbound.getId() + "."));

    inboundJpaEntity.setSupplierJpaEntity(supplierJpaEntity);
    inboundJpaEntity.setUserJpaEntity(userJpaEntity);
    inboundJpaEntity.setInvoiceNumber(inbound.getInvoiceNumber());
    inboundJpaEntity.setDateReceived(inbound.getDateReceived());

    for (InboundItemJpaEntity inboundItemJpaEntity : inboundJpaEntity.getListInboundItem()) {

      InboundItem inboundItem = mappedInboundItems.get(inboundItemJpaEntity.getId());

      ProductJpaEntity productJpaEntity = productJpaRepository
          .getReferenceBySku(inboundItem.getProductSku());

      ProductPackagingJpaEntity productPackagingJpaEntity = productPackagingJpaRepository
          .getReferenceById(inboundItem.getPackagingId());

      inboundItemJpaEntity.setProduct(productJpaEntity);
      inboundItemJpaEntity.setProductPackaging(productPackagingJpaEntity);
      inboundItemJpaEntity.setQuantityReceived(inboundItem.getQuantityReceived());
      inboundItemJpaEntity.setBaseQuantityEquivalent(inboundItem.getBaseQuantityEquivalent());
    }

    return inboundPersistenceMapper.toDomainEntity(inboundJpaEntity);
  }

  @Override
  public Optional<Inbound> findById(Long id) {
    Optional<InboundJpaEntity> optionalEntity = inboundJpaRepository.findById(id);
    return optionalEntity.map(
        entity -> inboundPersistenceMapper.toDomainEntity(entity));
  }

  @Override
  public Page<Inbound> findAll(Pageable pageable) {
    Page<InboundJpaEntity> pageableInbound = inboundJpaRepository.findAll(pageable);
    return pageableInbound.map(
        entity -> inboundPersistenceMapper.toDomainEntity(entity));
  }

  @Override
  public List<Inbound> findByDateReceived(LocalDateTime dateReceived) {
    // TODO Auto-generated method stub
    return null;
  }
}
