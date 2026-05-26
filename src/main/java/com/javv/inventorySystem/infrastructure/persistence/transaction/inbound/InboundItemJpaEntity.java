package com.javv.inventorySystem.infrastructure.persistence.transaction.inbound;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "inbound_item")
public class InboundItemJpaEntity {

  public InboundItemJpaEntity() {
  }
}
