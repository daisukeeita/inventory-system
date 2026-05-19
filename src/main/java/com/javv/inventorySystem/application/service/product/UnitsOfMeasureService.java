package com.javv.inventorySystem.application.service.product;

import java.util.Objects;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javv.inventorySystem.application.command.unitsOfMeasure.UnitsOfMeasureRegisterCommand;
import com.javv.inventorySystem.application.command.unitsOfMeasure.UnitsOfMeasureUpdateCommand;
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
  public UnitsOfMeasure saveMeasure(UnitsOfMeasureRegisterCommand unitsOfMeasureRegisterCommand) {
    UnitsOfMeasure unitsOfMeasure = registerToDomainEntity(unitsOfMeasureRegisterCommand);

    try {
      UnitsOfMeasure savedUnitsOfMeasure = unitsOfMeasureRepositoryInterface.save(unitsOfMeasure);
      return savedUnitsOfMeasure;

    } catch (DataIntegrityViolationException exception) {
      throw new EntityAlreadyExistsException("Service: Unit of Measure is already registered.");
    }
  }

  @Transactional
  public UnitsOfMeasure updateMeasure(
      Integer id, UnitsOfMeasureUpdateCommand unitsOfMeasureUpdateCommand) {
    Optional<UnitsOfMeasure> optionalMeasure = unitsOfMeasureRepositoryInterface.getById(id);

    UnitsOfMeasure unitsOfMeasure =
        optionalMeasure.orElseThrow(
            () ->
                new ResourceNotFoundException(
                    "Service: Units of Measure not found by id: '" + id + "'"));

    unitsOfMeasure.setName(unitsOfMeasureUpdateCommand.name());
    unitsOfMeasure.setAbbreviation(unitsOfMeasureUpdateCommand.abbreviation());

    try {
      UnitsOfMeasure savedUnitsOfMeasure = unitsOfMeasureRepositoryInterface.update(unitsOfMeasure);
      return savedUnitsOfMeasure;

    } catch (DataIntegrityViolationException exception) {
      throw new EntityAlreadyExistsException(
          "This unit of measure is already registered.", exception);
    }
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

  private UnitsOfMeasure registerToDomainEntity(
      UnitsOfMeasureRegisterCommand unitsOfMeasureRegisterCommand) {
    Objects.requireNonNull(
        unitsOfMeasureRegisterCommand,
        "Cannot map a null UnitsOfMeasureRegisterCommand to a Domain Entity.");

    UnitsOfMeasure unitsOfMeasure = new UnitsOfMeasure();
    unitsOfMeasure.setName(unitsOfMeasureRegisterCommand.name());
    unitsOfMeasure.setAbbreviation(unitsOfMeasureRegisterCommand.abbreviation());

    return unitsOfMeasure;
  }
}
