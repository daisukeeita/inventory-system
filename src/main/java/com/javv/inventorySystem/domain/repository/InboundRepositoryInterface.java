package com.javv.inventorySystem.domain.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.javv.inventorySystem.domain.model.transaction.inbound.Inbound;

public interface InboundRepositoryInterface {
  Inbound save(Inbound inbound);

  Inbound update(Inbound inbound);

  Optional<Inbound> findById(Long id);

  Page<Inbound> findAll(Pageable pageable);

  List<Inbound> findByDateReceived(LocalDateTime dateReceived);
}
