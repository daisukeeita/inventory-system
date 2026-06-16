package com.javv.inventorySystem.infrastructure.persistence.transaction.inbound;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InboundJpaRepository extends JpaRepository<InboundJpaEntity, Long> {

  Optional<InboundJpaEntity> findByDateReceived(LocalDateTime dateReceived);

  Page<InboundJpaEntity> findAll(Pageable pageable);
}
