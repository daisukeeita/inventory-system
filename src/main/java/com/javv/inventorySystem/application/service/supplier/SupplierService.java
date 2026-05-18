package com.javv.inventorySystem.application.service.supplier;

import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javv.inventorySystem.application.command.supplier.SupplierRegisterCommand;
import com.javv.inventorySystem.application.command.supplier.SupplierUpdateAddressCommand;
import com.javv.inventorySystem.domain.exception.EntityAlreadyExistsException;
import com.javv.inventorySystem.domain.exception.ResourceNotFoundException;
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
  public Supplier saveSupplier(SupplierRegisterCommand supplierRegisterCommand) {
    Supplier supplier = toDomainEntity(supplierRegisterCommand);

    try {
      return supplierRepositoryInterface.save(supplier);
    } catch (DataIntegrityViolationException exception) {
      throw new EntityAlreadyExistsException("Supplier already exists.");
    }
  }

  @Transactional
  public Supplier updateSupplierAddress(
      Integer id, SupplierUpdateAddressCommand supplierUpdateAddressCommand) {

    Supplier supplier = supplierRepositoryInterface
        .findById(id)
        .orElseThrow(
            () -> new ResourceNotFoundException("Supplier was not found using id: '" + id + "'"));

    supplier.updateAddress(
        supplierUpdateAddressCommand.street(),
        supplierUpdateAddressCommand.city(),
        supplierUpdateAddressCommand.state(),
        supplierUpdateAddressCommand.postalCode(),
        supplierUpdateAddressCommand.country());

    try {
      return supplierRepositoryInterface.updateAddress(supplier);
    } catch (DataIntegrityViolationException exception) {
      throw new EntityAlreadyExistsException("Supplier already exists.", exception.getCause());
    }
  }

  public Supplier findByName(String companyName) {
    Optional<Supplier> optionalSupplier = supplierRepositoryInterface.findByName(companyName);

    Supplier supplier = optionalSupplier.orElseThrow(
        () -> new ResourceNotFoundException(
            "Supplier was not found using companyName: ''" + companyName + "'"));

    return supplier;
  }

  public Supplier findById(int id) {
    Optional<Supplier> optionalSupplier = supplierRepositoryInterface.findById(id);

    Supplier supplier = optionalSupplier.orElseThrow(
        () -> new ResourceNotFoundException("Supplier was not found using ID: ''" + id + "'"));

    return supplier;
  }

  private Supplier toDomainEntity(SupplierRegisterCommand supplierRegisterCommand) {
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
