package com.javv.inventorySystem.infrastructure.persistence.unitsOfMeasure;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitsOfMeasureJpaRepository
    extends JpaRepository<UnitsOfMeasureJpaEntity, Integer> {

  @NativeQuery("SELECT DISTINCT units_of_measure.name from units_of_measure WHERE units_of_measure.name IN :names")
  List<String> findExistingName(@Param("names") List<String> names);

  @NativeQuery("SELECT name FROM units_of_measure")
  List<String> findAllNames();

  Optional<UnitsOfMeasureJpaEntity> findByName(String name);

  UnitsOfMeasureJpaEntity getReferenceByName(String name);

  Page<UnitsOfMeasureJpaEntity> findAll(Pageable pageable);

  boolean existsByName(String name);
}
