package com.javv.inventorySystem.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.javv.inventorySystem.domain.model.product.UnitsOfMeasure;

public interface UnitsOfMeasureRepositoryInterface {
  UnitsOfMeasure save(UnitsOfMeasure unitsOfMeasure);

  UnitsOfMeasure update(UnitsOfMeasure unitsOfMeasure);

  Optional<UnitsOfMeasure> findByName(String name);

  boolean existsByName(String name);

  List<String> findExistingNames(List<String> names);

  Page<UnitsOfMeasure> findAll(Pageable pageable);
}
