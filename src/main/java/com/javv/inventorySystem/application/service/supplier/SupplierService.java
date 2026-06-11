package com.javv.inventorySystem.application.service.supplier;

import java.util.Objects;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javv.inventorySystem.application.command.supplier.SupplierRegisterCommand;
import com.javv.inventorySystem.application.command.supplier.SupplierUpdateCommand;
import com.javv.inventorySystem.domain.exception.EntityAlreadyExistsException;
import com.javv.inventorySystem.domain.exception.ResourceNotFoundException;
import com.javv.inventorySystem.domain.exception.ServiceOperationException;
import com.javv.inventorySystem.domain.model.supplier.Supplier;
import com.javv.inventorySystem.domain.model.supplier.SupplierAddress;
import com.javv.inventorySystem.domain.repository.SupplierRepositoryInterface;

@Service
@Transactional(readOnly = true)
public class SupplierService {

  private SupplierRepositoryInterface supplierRepositoryInterface;

  public SupplierService(SupplierRepositoryInterface supplierRepositoryInterface) {
    this.supplierRepositoryInterface = supplierRepositoryInterface;
  }

  @Transactional
  public Supplier create(SupplierRegisterCommand supplierRegisterCommand) {

    try {
      Supplier supplier = toDomainEntity(supplierRegisterCommand);

      Supplier savedEntity = supplierRepositoryInterface.save(supplier);

      return savedEntity;
    } catch (DataIntegrityViolationException exception) {
      throw new EntityAlreadyExistsException(
          "Supplier Creation Failed: Supplier already exists.");
    } catch (NullPointerException exception) {
      throw new ServiceOperationException(
          "Supplier Creation Failed: " + exception.getMessage());
    }
  }

  @Transactional
  public Supplier update(
      Integer id, SupplierUpdateCommand supplierUpdateCommand) {
    try {
      Supplier supplier = getById(id);

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
    }
  }

  public Supplier getById(int id) {
    Optional<Supplier> optionalSupplier = supplierRepositoryInterface.findById(id);

    Supplier supplier = optionalSupplier.orElseThrow(
        () -> new ResourceNotFoundException("Supplier was not found using ID: ''" + id + "'"));

    return supplier;
  }

  public Supplier getByCompanyName(String companyName) {
    Optional<Supplier> optionalSupplier = supplierRepositoryInterface.findByCompanyName(companyName);

    Supplier supplier = optionalSupplier.orElseThrow(
        () -> new ResourceNotFoundException(
            "Supplier was not found using companyName: ''" + companyName + "'"));

    return supplier;
  }

  public Supplier getByEmail(String email) {
    Optional<Supplier> optionalSupplier = supplierRepositoryInterface.findByEmail(email);

    Supplier supplier = optionalSupplier.orElseThrow(
        () -> new ResourceNotFoundException(
            "Supplier was not found using email: ''" + email + "'"));

    return supplier;

  }

  public boolean checkIfExistsById(int id) {
    return supplierRepositoryInterface.existsById(id);
  }

  public boolean checkIfExistsByEmail(String email) {
    return supplierRepositoryInterface.existsByEmail(email);
  }

  private Supplier toDomainEntity(SupplierRegisterCommand supplierRegisterCommand) {

    Objects.requireNonNull(
        supplierRegisterCommand,
        "Cannot map a null SupplierRegisterCommand to a Domain Entity.");

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
