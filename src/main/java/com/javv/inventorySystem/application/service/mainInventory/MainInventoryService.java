package com.javv.inventorySystem.application.service.mainInventory;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javv.inventorySystem.application.command.mainInventory.MainInventoryRegisterCommand;
import com.javv.inventorySystem.application.command.mainInventory.MainInventoryResponseRead;
import com.javv.inventorySystem.application.service.product.ProductService;
import com.javv.inventorySystem.domain.exception.ResourceNotFoundException;
import com.javv.inventorySystem.domain.exception.ServiceOperationException;
import com.javv.inventorySystem.domain.model.mainInventory.MainInventory;
import com.javv.inventorySystem.domain.model.product.Product;
import com.javv.inventorySystem.domain.repository.MainInventoryRepositoryInterface;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional(readOnly = true)
public class MainInventoryService {

  @Autowired
  private MainInventoryRepositoryInterface mainInventoryRepositoryInterface;

  @Autowired
  private ProductService productService;

  @Transactional
  public MainInventoryResponseRead create(MainInventoryRegisterCommand mainInventoryRegisterCommand) {

    MainInventory mainInventory = toDomainEntity(mainInventoryRegisterCommand);

    try {
      return toResponseRead(
          mainInventoryRepositoryInterface.save(mainInventory));
    } catch (DataIntegrityViolationException exception) {
      throw new ServiceOperationException(
          "Main Inventory Creation Failed: The provided details conflict with an existing inventory.");
    } catch (EntityNotFoundException exception) {
      throw new ServiceOperationException(
          "Main Inventory Creation Failed: " + exception.getMessage() + ".");
    }
  }

  @Transactional
  public MainInventory getOrInitializeInventory(String productSku) {

    try {

      return mainInventoryRepositoryInterface.findByProductSku(productSku)
          .orElseGet(() -> {
            MainInventory mainInventory = new MainInventory();
            mainInventory.setProductSku(productSku);
            mainInventory.setQuantityOnHand(0);
            mainInventory.setReorderLevel(100);

            return mainInventoryRepositoryInterface.save(mainInventory);
          });
    } catch (DataIntegrityViolationException exception) {
      throw new ServiceOperationException(
          "Main Inventory Initialization Failed: The provided details conflict with an existing inventory.");
    }

  }

  @Transactional
  public void incrementStock(String productSku, int quantityReceived) {
    try {
      MainInventory mainInventory = getByProductSku(productSku);

      mainInventory.increaseStock(quantityReceived);

      mainInventoryRepositoryInterface.update(mainInventory);
    } catch (DataIntegrityViolationException exception) {
      throw new ServiceOperationException(
          "Inventory Increase Stock Failed: The provided details conflict with an existing inventory.");
    } catch (ResourceNotFoundException exception) {
      throw new ServiceOperationException(
          "Inventory Increase Stock Failed: Inventory must exist before incrementing stock. " + exception.getMessage());
    }
  }

  @Transactional
  public void decrementStock(String productSku, int quantityRetrieve) {
    // TODO: update this
  }

  public MainInventory getById(int id) {
    MainInventory mainInventory = mainInventoryRepositoryInterface
        .findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(
            "Inventory not found using ID: " + id + "."));

    return mainInventory;

  }

  public MainInventory getByProductSku(String productSku) {
    MainInventory mainInventory = mainInventoryRepositoryInterface
        .findByProductSku(productSku)
        .orElseThrow(() -> new ResourceNotFoundException(
            "Inventory not found with Product SKU: " + productSku + "."));

    return mainInventory;
  }

  public Page<MainInventory> getAllInventory(int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    return mainInventoryRepositoryInterface.findAll(pageable);
  }

  private MainInventory toDomainEntity(
      MainInventoryRegisterCommand mainInventoryRegisterCommand) {

    MainInventory mainInventory = new MainInventory();

    mainInventory.setProductSku(mainInventoryRegisterCommand.productSku());
    mainInventory.setQuantityOnHand(mainInventoryRegisterCommand.quantityOnHand());
    mainInventory.setReorderLevel(mainInventoryRegisterCommand.reorderLevel());

    return mainInventory;
  }

  private MainInventoryResponseRead toResponseRead(MainInventory mainInventory) {

    Objects.requireNonNull(
        mainInventory,
        "Main Inventory Service: Cannot map a null MainInventory to a Response Read Record.");

    Product product = productService.getBySku(mainInventory.getProductSku());

    return new MainInventoryResponseRead(
        mainInventory.getId(),
        product.getSku(),
        product.getName(),
        mainInventory.getQuantityOnHand(),
        mainInventory.getReorderLevel());
  }
}
