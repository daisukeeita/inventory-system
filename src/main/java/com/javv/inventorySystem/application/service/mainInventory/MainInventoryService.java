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
import com.javv.inventorySystem.domain.exception.EntityAlreadyExistsException;
import com.javv.inventorySystem.domain.model.mainInventory.MainInventory;
import com.javv.inventorySystem.domain.model.product.Product;
import com.javv.inventorySystem.domain.repository.MainInventoryRepositoryInterface;

import jakarta.persistence.EntityNotFoundException;

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
  public MainInventoryResponseRead saveInventory(MainInventoryRegisterCommand mainInventoryRegisterCommand) {

    MainInventory mainInventory = toDomainEntity(mainInventoryRegisterCommand);

    try {
      return toResponseRead(
          mainInventoryRepositoryInterface.save(mainInventory));
    } catch (DataIntegrityViolationException exception) {
      throw new EntityAlreadyExistsException(
          "Main Inventory Persistence Adapter: Inventory for this product already exists.");
    }
  }

  @Transactional
  public void updateInventory(MainInventoryUpdateCommand mainInventoryUpdateCommand) {

    MainInventory mainInventory = new MainInventory();
    mainInventory.setId(mainInventoryUpdateCommand.id());
    mainInventory.setProductId(mainInventoryUpdateCommand.productId());
    mainInventory.setQuantityOnHand(
        mainInventoryUpdateCommand.quantityOnHand());
    mainInventory.setReorderLevel(
        mainInventoryUpdateCommand.reorderLevel());

    mainInventoryRepositoryInterface.update(mainInventory);

  }

  @Transactional
  public void addStockInventory(Long productId, int quantityReceived) {

    MainInventory mainInventory = mainInventoryRepositoryInterface
        .getByProductId(productId)
        .orElseThrow(() -> new EntityNotFoundException(
            "Main Inventory Service: Inventory not found using the Product ID '" + productId + "'"));

    mainInventory.increaseStock(quantityReceived);

    mainInventoryRepositoryInterface.update(mainInventory);
  }

  public Page<MainInventory> getPageableInventory(int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    return mainInventoryRepositoryInterface.getPageable(pageable);
  }

  private MainInventory toDomainEntity(
      MainInventoryRegisterCommand mainInventoryRegisterCommand) {

    MainInventory mainInventory = new MainInventory();

    mainInventory.setProductId(mainInventoryRegisterCommand.productId());
    mainInventory.setQuantityOnHand(mainInventoryRegisterCommand.quantityOnHand());
    mainInventory.setReorderLevel(mainInventoryRegisterCommand.reorderLevel());

    return mainInventory;
  }

  private MainInventoryResponseRead toResponseRead(MainInventory mainInventory) {

    Objects.requireNonNull(
        mainInventory,
        "Main Inventory Service: Cannot map a null MainInventory to a Response Read Record.");

    Product product = productService.getById(mainInventory.getProductId());

    return new MainInventoryResponseRead(
        mainInventory.getId(),
        product.getSku(),
        product.getName(),
        mainInventory.getQuantityOnHand(),
        mainInventory.getReorderLevel());
  }
}
