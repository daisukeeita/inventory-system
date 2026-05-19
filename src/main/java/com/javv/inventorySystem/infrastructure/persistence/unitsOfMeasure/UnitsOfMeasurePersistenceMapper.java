package com.javv.inventorySystem.infrastructure.persistence.unitsOfMeasure;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.javv.inventorySystem.domain.model.product.UnitsOfMeasure;

@Component
public class UnitsOfMeasurePersistenceMapper {

  public UnitsOfMeasureJpaEntity toJpaEntity(UnitsOfMeasure unitsOfMeasure) {

    Objects.requireNonNull(
        unitsOfMeasure,
        "Measure Persistence Mapper: Cannot map a null UnitsOfMeasure to JPA Entity.");

    UnitsOfMeasureJpaEntity unitsOfMeasureJpaEntity = new UnitsOfMeasureJpaEntity();
    unitsOfMeasureJpaEntity.setId(unitsOfMeasure.getId());
    unitsOfMeasureJpaEntity.setName(unitsOfMeasure.getName());
    unitsOfMeasureJpaEntity.setAbbreviation(unitsOfMeasure.getAbbreviation());
    unitsOfMeasureJpaEntity.setCreatedAt(unitsOfMeasure.getCreatedAt());
    unitsOfMeasureJpaEntity.setUpdatedAt(unitsOfMeasure.getUpdatedAt());

    return unitsOfMeasureJpaEntity;
  }

  public UnitsOfMeasure toDomainEntity(UnitsOfMeasureJpaEntity unitsOfMeasureJpaEntity) {

    Objects.requireNonNull(
        unitsOfMeasureJpaEntity,
        "Measure Persistence Mapper: Cannot map a null UnitsOfMeasureJpaEntity to Domain Entity.");

    UnitsOfMeasure unitsOfMeasure = new UnitsOfMeasure();
    unitsOfMeasure.setId(unitsOfMeasureJpaEntity.getId());
    unitsOfMeasure.setName(unitsOfMeasureJpaEntity.getName());
    unitsOfMeasure.setAbbreviation(unitsOfMeasureJpaEntity.getAbbreviation());
    unitsOfMeasure.setCreatedAt(unitsOfMeasureJpaEntity.getCreatedAt());
    unitsOfMeasure.setUpdatedAt(unitsOfMeasureJpaEntity.getUpdatedAt());

    return unitsOfMeasure;
  }
}
