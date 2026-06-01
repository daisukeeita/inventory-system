package com.javv.inventorySystem.application.service.mainInventory;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javv.inventorySystem.application.command.mainInventory.MainInventoryRegisterCommand;
import com.javv.inventorySystem.application.service.product.ProductService;
import com.javv.inventorySystem.domain.exception.EntityAlreadyExistsException;
import com.javv.inventorySystem.domain.model.mainInventory.MainInventory;
import com.javv.inventorySystem.domain.model.product.Product;
import com.javv.inventorySystem.domain.repository.MainInventoryRepositoryInterface;

@Service
@Transactional(readOnly = true)
public class MainInventoryService {

  private ProductService productService;
  private MainInventoryRepositoryInterface mainInventoryRepositoryInterface;

  public MainInventoryService(
      ProductService productService,
      MainInventoryRepositoryInterface mainInventoryRepositoryInterface) {
    this.productService = productService;
    this.mainInventoryRepositoryInterface = mainInventoryRepositoryInterface;
  }

  @Transactional
  public MainInventory saveInventory(MainInventoryRegisterCommand mainInventoryRegisterCommand) {
    Product product = productService.getBySku(mainInventoryRegisterCommand.sku());

    MainInventory mainInventory = toDomainEntity(mainInventoryRegisterCommand, product);

    try {
      return mainInventoryRepositoryInterface.save(mainInventory);
    } catch (DataIntegrityViolationException exception) {
      throw new EntityAlreadyExistsException(
          "Main Inventory Persistence Adapter: Inventory for this product already exists.");
    }
  }

  private MainInventory toDomainEntity(
      MainInventoryRegisterCommand mainInventoryRegisterCommand,
      Product product) {

    MainInventory mainInventory = new MainInventory();

    mainInventory.setProductSku(product.getSku());
    mainInventory.setQuantityOnHand(mainInventoryRegisterCommand.quantityOnHand());
    mainInventory.setReorderLevel(mainInventoryRegisterCommand.reorderLevel());

    return mainInventory;
  }
}
