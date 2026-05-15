package com.javv.inventorySystem.application.service.product;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javv.inventorySystem.application.command.product.UnitsOfMeasureCommand;
import com.javv.inventorySystem.domain.exception.ResourceNotFoundException;
import com.javv.inventorySystem.domain.model.product.UnitsOfMeasure;
import com.javv.inventorySystem.domain.repository.UnitsOfMeasureRepositoryInterface;

@Service
@Transactional
public class UnitsOfMeasureService {

  private UnitsOfMeasureRepositoryInterface unitsOfMeasureRepositoryInterface;

  public UnitsOfMeasureService(
      UnitsOfMeasureRepositoryInterface unitsOfMeasureRepositoryInterface) {
    this.unitsOfMeasureRepositoryInterface = unitsOfMeasureRepositoryInterface;
  }

  @Transactional
  public UnitsOfMeasure saveMeasure(UnitsOfMeasureCommand unitsOfMeasureCommand) {
    UnitsOfMeasure unitsOfMeasure = toDomainEntity(unitsOfMeasureCommand);

    UnitsOfMeasure savedUnitsOfMeasure = unitsOfMeasureRepositoryInterface.save(unitsOfMeasure);

    return savedUnitsOfMeasure;
  }

  public UnitsOfMeasure getById(int id) {
    Optional<UnitsOfMeasure> optionalUnitsOfMeasure = unitsOfMeasureRepositoryInterface.getById(id);

    return optionalUnitsOfMeasure.orElseThrow(
        () -> new ResourceNotFoundException("Units of Measure not found by id: ;" + id + "'"));
  }

  public UnitsOfMeasure getByName(String name) {
    Optional<UnitsOfMeasure> optionalUnitsOfMeasure =
        unitsOfMeasureRepositoryInterface.getByName(name);

    return optionalUnitsOfMeasure.orElseThrow(
        () -> new ResourceNotFoundException("Units of Measure not found by name: '" + name + "'"));
  }

  private UnitsOfMeasure toDomainEntity(UnitsOfMeasureCommand unitsOfMeasureCommand) {
    UnitsOfMeasure unitsOfMeasure = new UnitsOfMeasure();
    unitsOfMeasure.setName(unitsOfMeasureCommand.name());
    unitsOfMeasure.setAbbreviation(unitsOfMeasureCommand.abbreviation());

    return unitsOfMeasure;
  }
}
