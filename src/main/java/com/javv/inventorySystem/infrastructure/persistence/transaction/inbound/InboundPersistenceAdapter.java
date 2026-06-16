package com.javv.inventorySystem.infrastructure.persistence.transaction.inbound;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.javv.inventorySystem.domain.model.transaction.inbound.Inbound;
import com.javv.inventorySystem.domain.repository.InboundRepositoryInterface;
import com.javv.inventorySystem.infrastructure.persistence.product.ProductJpaRepository;
import com.javv.inventorySystem.infrastructure.persistence.productPackaging.ProductPackagingJpaRepository;
import com.javv.inventorySystem.infrastructure.persistence.supplier.SupplierJpaRepository;
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
