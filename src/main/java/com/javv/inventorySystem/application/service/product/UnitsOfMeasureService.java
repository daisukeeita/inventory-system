package com.javv.inventorySystem.application.service.product;

import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javv.inventorySystem.application.command.unitsOfMeasure.UnitsOfMeasureRegisterCommand;
import com.javv.inventorySystem.application.command.unitsOfMeasure.UnitsOfMeasureUpdateCommand;
import com.javv.inventorySystem.domain.exception.ObjectMappingException;
import com.javv.inventorySystem.domain.exception.ResourceNotFoundException;
import com.javv.inventorySystem.domain.exception.ServiceOperationException;
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
    UnitsOfMeasure unitsOfMeasure = toDomainEntity(unitsOfMeasureRegisterCommand);

    try {
      UnitsOfMeasure savedUnitsOfMeasure = unitsOfMeasureRepositoryInterface.save(unitsOfMeasure);

      return savedUnitsOfMeasure;
    } catch (DataIntegrityViolationException exception) {
      throw new ServiceOperationException(
          "Units of Measure Creation Failed: The provided details conflict with an existing units of measure.");
    }
  }

  @Transactional
  public UnitsOfMeasure updateMeasure(
      int id, UnitsOfMeasureUpdateCommand unitsOfMeasureUpdateCommand) {

    try {
      UnitsOfMeasure unitsOfMeasure = getById(id);

      unitsOfMeasure.setName(unitsOfMeasureUpdateCommand.name());
      unitsOfMeasure.setAbbreviation(unitsOfMeasureUpdateCommand.abbreviation());

      UnitsOfMeasure savedUnitsOfMeasure = unitsOfMeasureRepositoryInterface.update(unitsOfMeasure);

      return savedUnitsOfMeasure;

    } catch (DataIntegrityViolationException exception) {
      throw new ServiceOperationException(
          "Units of Measure Update Failed: The provided details conflict with an existing units of measure.");
    } catch (ResourceNotFoundException exception) {
      throw new ServiceOperationException(
          "Units of Measure Update Failed: " + exception.getMessage());
    }
  }

  public UnitsOfMeasure getById(int id) {
    Optional<UnitsOfMeasure> optionalUnitsOfMeasure = unitsOfMeasureRepositoryInterface.findById(id);

    return optionalUnitsOfMeasure.orElseThrow(
        () -> new ResourceNotFoundException("Units of Measure not found by id: " + id + "."));
  }

  public UnitsOfMeasure getByName(String name) {
    Optional<UnitsOfMeasure> optionalUnitsOfMeasure = unitsOfMeasureRepositoryInterface.findByName(name);

    return optionalUnitsOfMeasure.orElseThrow(
        () -> new ResourceNotFoundException("Units of Measure not found by name: " + name + "."));
  }

  private UnitsOfMeasure toDomainEntity(
      UnitsOfMeasureRegisterCommand unitsOfMeasureRegisterCommand) {

    if (unitsOfMeasureRegisterCommand == null) {
      throw new ObjectMappingException(
          "Units of Measure Service: Cannot map a null UnitsOfMeasureRegisterCommand to a Domain Entity.");
    }

    UnitsOfMeasure unitsOfMeasure = new UnitsOfMeasure();
    unitsOfMeasure.setName(unitsOfMeasureRegisterCommand.name());
    unitsOfMeasure.setAbbreviation(unitsOfMeasureRegisterCommand.abbreviation());

    return unitsOfMeasure;
  }
}
