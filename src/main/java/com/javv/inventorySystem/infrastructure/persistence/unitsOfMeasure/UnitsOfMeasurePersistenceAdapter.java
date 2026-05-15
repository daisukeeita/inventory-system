package com.javv.inventorySystem.infrastructure.persistence.unitsOfMeasure;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.javv.inventorySystem.domain.model.product.UnitsOfMeasure;
import com.javv.inventorySystem.domain.repository.UnitsOfMeasureRepositoryInterface;

@Repository
public class UnitsOfMeasurePersistenceAdapter implements UnitsOfMeasureRepositoryInterface {

  private UnitsOfMeasureJpaRepository unitsOfMeasureJpaRepository;
  private UnitsOfMeasurePersistenceMapper unitsOfMeasurePersistenceMapper;

  public UnitsOfMeasurePersistenceAdapter(
      UnitsOfMeasureJpaRepository unitsOfMeasureJpaRepository,
      UnitsOfMeasurePersistenceMapper unitsOfMeasurePersistenceMapper) {
    this.unitsOfMeasureJpaRepository = unitsOfMeasureJpaRepository;
    this.unitsOfMeasurePersistenceMapper = unitsOfMeasurePersistenceMapper;
  }

  @Override
  public UnitsOfMeasure save(UnitsOfMeasure unitsOfMeasure) {
    UnitsOfMeasureJpaEntity unitsOfMeasureJpaEntity =
        unitsOfMeasurePersistenceMapper.toJpaEntity(unitsOfMeasure);

    UnitsOfMeasureJpaEntity savedEntity = unitsOfMeasureJpaRepository.save(unitsOfMeasureJpaEntity);

    return unitsOfMeasurePersistenceMapper.toDomainEntity(savedEntity);
  }

  @Override
  public Optional<UnitsOfMeasure> getById(int id) {
    Optional<UnitsOfMeasureJpaEntity> optionalEntity = unitsOfMeasureJpaRepository.findById(id);

    return optionalEntity.map(entity -> unitsOfMeasurePersistenceMapper.toDomainEntity(entity));
  }

  @Override
  public Optional<UnitsOfMeasure> getByName(String name) {
    Optional<UnitsOfMeasureJpaEntity> optionalEntity = unitsOfMeasureJpaRepository.findByName(name);

    return optionalEntity.map(entity -> unitsOfMeasurePersistenceMapper.toDomainEntity(entity));
  }
}
