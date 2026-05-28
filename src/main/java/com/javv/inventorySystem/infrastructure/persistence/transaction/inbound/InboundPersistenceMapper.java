package com.javv.inventorySystem.infrastructure.persistence.transaction.inbound;

import org.springframework.stereotype.Component;

import com.javv.inventorySystem.domain.model.transaction.inbound.Inbound;

@Component
public class InboundPersistenceMapper {

  public InboundJpaEntity toJpaEntity(Inbound inbound) {
    return null;
  }

  public Inbound toDomainEntity(InboundJpaEntity inboundJpaEntity) {
    return null;
  }

  public InboundJpaEntity updateJpaEntity(Inbound inbound, InboundJpaEntity inboundJpaEntity) {
    return null;
  }
}
