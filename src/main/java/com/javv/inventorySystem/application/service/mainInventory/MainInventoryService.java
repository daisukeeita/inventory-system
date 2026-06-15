package com.javv.inventorySystem.application.service.mainInventory;

import java.util.Objects;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javv.inventorySystem.application.command.mainInventory.MainInventoryRegisterCommand;
import com.javv.inventorySystem.application.command.mainInventory.MainInventoryResponseRead;
import com.javv.inventorySystem.application.command.mainInventory.MainInventoryUpdateCommand;
import com.javv.inventorySystem.application.service.product.ProductService;
import com.javv.inventorySystem.domain.exception.ResourceNotFoundException;
import com.javv.inventorySystem.domain.exception.ServiceOperationException;
import com.javv.inventorySystem.domain.model.mainInventory.MainInventory;
import com.javv.inventorySystem.domain.model.product.Product;
import com.javv.inventorySystem.domain.repository.MainInventoryRepositoryInterface;

@Service
@Transactional(readOnly = true)
public class MainInventoryService {

  private MainInventoryRepositoryInterface mainInventoryRepositoryInterface;
  private ProductService productService;

  public MainInventoryService(
      ProductService productService,
      MainInventoryRepositoryInterface mainInventoryRepositoryInterface) {
    this.productService = productService;
    this.mainInventoryRepositoryInterface = mainInventoryRepositoryInterface;
  }

  @Transactional
  public MainInventoryResponseRead create(MainInventoryRegisterCommand mainInventoryRegisterCommand) {

    MainInventory mainInventory = toDomainEntity(mainInventoryRegisterCommand);

    try {
      return toResponseRead(
          mainInventoryRepositoryInterface.save(mainInventory));
    } catch (DataIntegrityViolationException exception) {
      throw new ServiceOperationException(
          "Main Inventory Creation Failed: Inventory for this product already exists.");
    }
  }

  @Transactional
  public void update(MainInventoryUpdateCommand mainInventoryUpdateCommand) {

    MainInventory mainInventory = new MainInventory();
    mainInventory.setId(mainInventoryUpdateCommand.id());
    mainInventory.setProductSku(mainInventoryUpdateCommand.productId());
    mainInventory.setQuantityOnHand(
        mainInventoryUpdateCommand.quantityOnHand());
    mainInventory.setReorderLevel(
        mainInventoryUpdateCommand.reorderLevel());

    mainInventoryRepositoryInterface.update(mainInventory);

  }

  @Transactional
  public void addStockInventory(Long productId, int quantityReceived) {
    try {
      MainInventory mainInventory = getByProductId(productId);

      mainInventory.increaseStock(quantityReceived);

      mainInventoryRepositoryInterface.update(mainInventory);
    } catch (DataIntegrityViolationException exception) {
      throw new ServiceOperationException(
          "Inventory Increase Stock Failed: The provided details conflict with an existing product.");
    } catch (ResourceNotFoundException exception) {
      throw new ServiceOperationException(
          "Inventory Increase Stock Failed: " + exception.getMessage());
    }
  }

  public MainInventory getById(int id) {
    MainInventory mainInventory = mainInventoryRepositoryInterface
        .findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(
            "Inventory not found using ID: " + id + "."));

    return mainInventory;

  }

  public MainInventory getByProductId(Long productId) {
    MainInventory mainInventory = mainInventoryRepositoryInterface
        .findByProductId(productId)
        .orElseThrow(() -> new ResourceNotFoundException(
            "Inventory not found using Product ID: " + productId + "."));

    return mainInventory;
  }

  public MainInventory getOrInitializeInventory(Long productId) {
    return null;
  }

  public Page<MainInventory> getAllInventory(int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    return mainInventoryRepositoryInterface.findAll(pageable);
  }

  private MainInventory toDomainEntity(
      MainInventoryRegisterCommand mainInventoryRegisterCommand) {

    MainInventory mainInventory = new MainInventory();

    mainInventory.setProductSku(mainInventoryRegisterCommand.productId());
    mainInventory.setQuantityOnHand(mainInventoryRegisterCommand.quantityOnHand());
    mainInventory.setReorderLevel(mainInventoryRegisterCommand.reorderLevel());

    return mainInventory;
  }

  private MainInventoryResponseRead toResponseRead(MainInventory mainInventory) {

    Objects.requireNonNull(
        mainInventory,
        "Main Inventory Service: Cannot map a null MainInventory to a Response Read Record.");

    Product product = productService.getById(mainInventory.getProductSku());

    return new MainInventoryResponseRead(
        mainInventory.getId(),
        product.getSku(),
        product.getName(),
        mainInventory.getQuantityOnHand(),
        mainInventory.getReorderLevel());
  }
}
