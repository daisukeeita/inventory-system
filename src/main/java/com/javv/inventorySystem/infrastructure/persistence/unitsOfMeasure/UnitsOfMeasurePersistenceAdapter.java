package com.javv.inventorySystem.infrastructure.persistence.unitsOfMeasure;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.javv.inventorySystem.domain.exception.ResourceNotFoundException;
import com.javv.inventorySystem.domain.model.product.UnitsOfMeasure;
import com.javv.inventorySystem.domain.repository.UnitsOfMeasureRepositoryInterface;

@Repository
public class UnitsOfMeasurePersistenceAdapter implements UnitsOfMeasureRepositoryInterface {

  @Autowired
  private UnitsOfMeasureJpaRepository unitsOfMeasureJpaRepository;

  @Autowired
  private UnitsOfMeasurePersistenceMapper unitsOfMeasurePersistenceMapper;

  @Override
  public UnitsOfMeasure save(UnitsOfMeasure unitsOfMeasure) {
    UnitsOfMeasureJpaEntity unitsOfMeasureJpaEntity = unitsOfMeasurePersistenceMapper.toJpaEntity(unitsOfMeasure);

    UnitsOfMeasureJpaEntity savedEntity = unitsOfMeasureJpaRepository.saveAndFlush(unitsOfMeasureJpaEntity);

    return unitsOfMeasurePersistenceMapper.toDomainEntity(savedEntity);
  }

  @Override
  public UnitsOfMeasure update(UnitsOfMeasure unitsOfMeasure) {

    UnitsOfMeasureJpaEntity fetchedEntity = unitsOfMeasureJpaRepository
        .findById(unitsOfMeasure.getId())
        .orElseThrow(() -> new ResourceNotFoundException(
            "Units of Measure Persistence: Units of Measure not found using ID: " + unitsOfMeasure.getId() + "."));

    UnitsOfMeasureJpaEntity updatedJpaEntity = unitsOfMeasurePersistenceMapper
        .updateJpaEntity(unitsOfMeasure, fetchedEntity);

    UnitsOfMeasureJpaEntity savedEntity = unitsOfMeasureJpaRepository.saveAndFlush(updatedJpaEntity);

    return unitsOfMeasurePersistenceMapper.toDomainEntity(savedEntity);
  }

  @Override
  public Optional<UnitsOfMeasure> findById(int id) {
    Optional<UnitsOfMeasureJpaEntity> optionalEntity = unitsOfMeasureJpaRepository.findById(id);

    return optionalEntity.map(entity -> unitsOfMeasurePersistenceMapper.toDomainEntity(entity));
  }

  @Override
  public Optional<UnitsOfMeasure> findByName(String name) {
    Optional<UnitsOfMeasureJpaEntity> optionalEntity = unitsOfMeasureJpaRepository.findByName(name);

    return optionalEntity.map(entity -> unitsOfMeasurePersistenceMapper.toDomainEntity(entity));
  }

  @Override
  public boolean existsById(int id) {
    return unitsOfMeasureJpaRepository.existsById(id);
  }

  @Override
  public Long countByIdIn(List<Integer> listId) {
    return unitsOfMeasureJpaRepository.countByIdIn(listId);
  }

  @Override
  public List<UnitsOfMeasure> findAllById(List<Integer> listId) {
    List<UnitsOfMeasureJpaEntity> listJpaEntity = unitsOfMeasureJpaRepository.findAllById(listId);

    return listJpaEntity.stream()
        .map(entity -> unitsOfMeasurePersistenceMapper.toDomainEntity(entity))
        .toList();
  }

  @Override
  public Page<UnitsOfMeasure> findAll(Pageable pageable) {
    Page<UnitsOfMeasureJpaEntity> pageableMeasure = unitsOfMeasureJpaRepository.findAll(pageable);

    return pageableMeasure.map(entity -> unitsOfMeasurePersistenceMapper.toDomainEntity(entity));
  }
}
