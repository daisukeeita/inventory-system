package com.javv.inventorySystem.infrastructure.persistence.transaction.inbound;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.javv.inventorySystem.domain.model.transaction.inbound.Inbound;
import com.javv.inventorySystem.domain.repository.InboundRepositoryInterface;

@Repository
public class InboundPersistenceAdapter implements InboundRepositoryInterface {

  private InboundJpaRepository inboundJpaRepository;

  public InboundPersistenceAdapter(InboundJpaRepository inboundJpaRepository) {
    this.inboundJpaRepository = inboundJpaRepository;
  }

  @Override
  public Inbound save(Inbound inbound) {
    return null;
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
