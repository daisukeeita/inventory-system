package com.javv.inventorySystem.application.service.supplier;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javv.inventorySystem.application.command.supplier.SupplierRegisterCommand;
import com.javv.inventorySystem.application.command.supplier.SupplierUpdateCommand;
import com.javv.inventorySystem.domain.exception.EntityAlreadyExistsException;
import com.javv.inventorySystem.domain.exception.ObjectMappingException;
import com.javv.inventorySystem.domain.exception.ResourceNotFoundException;
import com.javv.inventorySystem.domain.exception.ServiceOperationException;
import com.javv.inventorySystem.domain.model.supplier.Supplier;
import com.javv.inventorySystem.domain.model.supplier.SupplierAddress;
import com.javv.inventorySystem.domain.repository.SupplierRepositoryInterface;

@Service
@Transactional(readOnly = true)
public class SupplierService {

  @Autowired
  private SupplierRepositoryInterface supplierRepositoryInterface;

  @Transactional
  public Supplier create(SupplierRegisterCommand supplierRegisterCommand) {

    try {
      Supplier supplier = toDomainEntity(supplierRegisterCommand);

      String supplierCode = "SUP-100" + (supplierRepositoryInterface.count() + 1);

      supplier.setSupplierCode(supplierCode);

      Supplier savedEntity = supplierRepositoryInterface.save(supplier);

      return savedEntity;
    } catch (DataIntegrityViolationException exception) {
      throw new EntityAlreadyExistsException(
          "Supplier Creation Failed: The provided details conflict with an existing supplier.");
    } catch (ObjectMappingException exception) {
      throw new ServiceOperationException(
          "Supplier Creation Failed: " + exception.getMessage());
    }
  }

  @Transactional
  public Supplier update(String supplierCode, SupplierUpdateCommand supplierUpdateCommand) {

    try {
      Supplier supplier = getBySupplierCode(supplierCode);

      supplier.setCompanyName(supplierUpdateCommand.companyName());
      supplier.setContactName(supplierUpdateCommand.contactName());
      supplier.setPhoneNumber(supplierUpdateCommand.phoneNumber());
      supplier.setEmail(supplierUpdateCommand.email());

      supplier.updateAddress(
          supplierUpdateCommand.street(),
          supplierUpdateCommand.city(),
          supplierUpdateCommand.state(),
          supplierUpdateCommand.postalCode(),
          supplierUpdateCommand.country());

      return supplierRepositoryInterface.update(supplier);
    } catch (DataIntegrityViolationException exception) {
      throw new ServiceOperationException(
          "Supplier Update Failed: The provided details conflict with an existing supplier.",
          exception);
    } catch (ResourceNotFoundException exception) {
      throw new ServiceOperationException(
          "Supplier Update Failed: " + exception.getMessage(),
          exception);
    } catch (ObjectMappingException exception) {
      throw new ServiceOperationException(
          "Supplier Update Failed: " + exception.getMessage(),
          exception);
    }
  }

  public Supplier getBySupplierCode(String supplierCode) {
    Optional<Supplier> optionalSupplier = supplierRepositoryInterface.findBySupplierCode(supplierCode);

    Supplier supplier = optionalSupplier.orElseThrow(
        () -> new ResourceNotFoundException(
            "Supplier was not found with supplier code: " + supplierCode + "."));

    return supplier;
  }

  public boolean checkIfExistsBySupplierCode(String supplierCoe) {
    boolean result = supplierRepositoryInterface.existsBySupplierCode(supplierCoe);

    return result;
  }

  private Supplier toDomainEntity(SupplierRegisterCommand supplierRegisterCommand) {

    if (supplierRegisterCommand == null) {
      throw new ObjectMappingException(
          "Cannot map a null SupplierRegisterCommand to a Domain Entity.");
    }

    SupplierAddress supplierAddress = new SupplierAddress();
    supplierAddress.setStreet(supplierRegisterCommand.street());
    supplierAddress.setCity(supplierRegisterCommand.city());
    supplierAddress.setState(supplierRegisterCommand.state());
    supplierAddress.setPostalCode(supplierRegisterCommand.postalCode());
    supplierAddress.setCountry(supplierRegisterCommand.country());

    Supplier supplier = new Supplier();
    supplier.setCompanyName(supplierRegisterCommand.companyName());
    supplier.setContactName(supplierRegisterCommand.contactName());
    supplier.setPhoneNumber(supplierRegisterCommand.phoneNumber());
    supplier.setEmail(supplierRegisterCommand.email());
    supplier.setSupplierAddress(supplierAddress);

    return supplier;
  }
}
