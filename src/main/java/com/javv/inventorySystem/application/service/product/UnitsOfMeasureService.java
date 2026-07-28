package com.javv.inventorySystem.application.service.product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

  @Autowired
  private UnitsOfMeasureRepositoryInterface unitsOfMeasureRepositoryInterface;

  @Transactional
  public UnitsOfMeasure create(UnitsOfMeasureRegisterCommand unitsOfMeasureRegisterCommand) {

    try {
      UnitsOfMeasure unitsOfMeasure = toDomainEntity(unitsOfMeasureRegisterCommand);

      UnitsOfMeasure savedUnitsOfMeasure = unitsOfMeasureRepositoryInterface.save(unitsOfMeasure);

      return savedUnitsOfMeasure;
    } catch (DataIntegrityViolationException exception) {
      throw new ServiceOperationException(
          "Units of Measure Creation Failed: The provided details conflict with an existing units of measure.");
    } catch (ObjectMappingException exception) {
      throw new ServiceOperationException(
          "Units of Measure Creation Failed: " + exception.getMessage());
    }
  }

  @Transactional
  public UnitsOfMeasure update(
      String name, UnitsOfMeasureUpdateCommand unitsOfMeasureUpdateCommand) {

    try {
      UnitsOfMeasure unitsOfMeasure = getByName(name);

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

  public UnitsOfMeasure getByName(String name) {
    Optional<UnitsOfMeasure> optionalUnitsOfMeasure = unitsOfMeasureRepositoryInterface.findByName(name);

    return optionalUnitsOfMeasure.orElseThrow(
        () -> new ResourceNotFoundException("Units of Measure not found by name: " + name + "."));
  }

  public boolean checkIfExistsByName(String name) {
    return unitsOfMeasureRepositoryInterface.existsByName(name);
  }

  public boolean doAllNamesExists(List<String> names) {

    if (names == null || names.isEmpty()) {
      return false;
    }

    List<String> filteredLists = names
        .stream()
        .distinct()
        .toList();

    List<String> existingLists = unitsOfMeasureRepositoryInterface
        .findExistingNames(filteredLists);

    return filteredLists.size() == existingLists.size();
  }

  public Page<UnitsOfMeasure> getAll(int page, int size) {
    Pageable pageable = PageRequest.of(page, size);

    return unitsOfMeasureRepositoryInterface.findAll(pageable);
  }

  public List<String> getAllNames() {
    List<String> listUomNames = unitsOfMeasureRepositoryInterface.findAllNames();

    return listUomNames;
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
