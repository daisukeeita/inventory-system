package com.javv.inventorySystem.domain.repository;

import java.util.List;
import java.util.Optional;

import com.javv.inventorySystem.domain.model.transaction.inbound.Inbound;

public interface InboundRepositoryInterface {
  Inbound save(Inbound inbound);

  Inbound update(Inbound inbound);

  Optional<Inbound> getById(Long id);

  List<Inbound> getAll();

  List<Inbound> getListByDate();
}
