package com.javv.inventorySystem.application.service.product;

import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javv.inventorySystem.application.command.product.UnitsOfMeasureCommand;
import com.javv.inventorySystem.domain.exception.EntityAlreadyExistsException;
import com.javv.inventorySystem.domain.exception.ResourceNotFoundException;
import com.javv.inventorySystem.domain.model.product.UnitsOfMeasure;
import com.javv.inventorySystem.domain.repository.UnitsOfMeasureRepositoryInterface;

@Service
@Transactional(readOnly = true)
public class UnitsOfMeasureService {

  private UnitsOfMeasureRepositoryInterface unitsOfMeasureRepositoryInterface;

  public UnitsOfMeasureService(
      UnitsOfMeasureRepositoryInterface unitsOfMeasureRepositoryInterface) {
    this.unitsOfMeasureRepositoryInterface = unitsOfMeasureRepositoryInterface;
  }

  @Transactional
  public UnitsOfMeasure saveMeasure(UnitsOfMeasureCommand unitsOfMeasureCommand) {
    UnitsOfMeasure unitsOfMeasure = toDomainEntity(unitsOfMeasureCommand);

    try {
      UnitsOfMeasure savedUnitsOfMeasure = unitsOfMeasureRepositoryInterface.save(unitsOfMeasure);
      return savedUnitsOfMeasure;

    } catch (DataIntegrityViolationException exception) {
      throw new EntityAlreadyExistsException(
          "This unit of measure is already registered.", exception);
    }
  }

  @Transactional
  public UnitsOfMeasure updateMeasure(Integer id, UnitsOfMeasureCommand unitsOfMeasureCommand) {
    Optional<UnitsOfMeasure> optionalMeasure = unitsOfMeasureRepositoryInterface.getById(id);

    UnitsOfMeasure unitsOfMeasure =
        optionalMeasure.orElseThrow(
            () -> new ResourceNotFoundException("Units of Measure not found by id: ;" + id + "'"));
    unitsOfMeasure.setName(unitsOfMeasureCommand.name());
    unitsOfMeasure.setAbbreviation(unitsOfMeasureCommand.abbreviation());

    UnitsOfMeasure savedMeasure = unitsOfMeasureRepositoryInterface.save(unitsOfMeasure);

    return savedMeasure;
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
