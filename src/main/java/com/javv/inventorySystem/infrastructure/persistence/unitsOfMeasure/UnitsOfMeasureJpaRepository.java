package com.javv.inventorySystem.infrastructure.persistence.unitsOfMeasure;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitsOfMeasureJpaRepository
    extends JpaRepository<UnitsOfMeasureJpaEntity, Integer> {

  Optional<UnitsOfMeasureJpaEntity> findByName(String name);
}
