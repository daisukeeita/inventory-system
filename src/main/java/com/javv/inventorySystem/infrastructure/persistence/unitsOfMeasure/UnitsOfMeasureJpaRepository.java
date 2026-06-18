package com.javv.inventorySystem.infrastructure.persistence.unitsOfMeasure;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitsOfMeasureJpaRepository
    extends JpaRepository<UnitsOfMeasureJpaEntity, Integer> {

  @Query("SELECT DISTINCT units_of_measure.name from units_of_measure WHERE units_of_measure.name IN :names")
  List<String> findExistingNames(@Param("names") List<String> names);

  Optional<UnitsOfMeasureJpaEntity> findByName(String name);

  Page<UnitsOfMeasureJpaEntity> findAll(Pageable pageable);

}
