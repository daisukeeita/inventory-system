package com.javv.inventorySystem.domain.repository;

import java.util.Optional;

import com.javv.inventorySystem.domain.model.product.UnitsOfMeasure;

public interface UnitsOfMeasureRepositoryInterface {
  UnitsOfMeasure save(UnitsOfMeasure unitsOfMeasure);

  UnitsOfMeasure update(UnitsOfMeasure unitsOfMeasure);

  Optional<UnitsOfMeasure> getById(Integer id);

  Optional<UnitsOfMeasure> getByName(String name);
}
