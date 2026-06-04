package com.javv.inventorySystem.application.service.mainInventory;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javv.inventorySystem.application.command.mainInventory.MainInventoryRegisterCommand;
import com.javv.inventorySystem.application.command.mainInventory.MainInventoryUpdateCommand;
import com.javv.inventorySystem.domain.exception.EntityAlreadyExistsException;
import com.javv.inventorySystem.domain.model.mainInventory.MainInventory;
import com.javv.inventorySystem.domain.repository.MainInventoryRepositoryInterface;

@Service
@Transactional(readOnly = true)
public class MainInventoryService {

  private MainInventoryRepositoryInterface mainInventoryRepositoryInterface;

  public MainInventoryService(
      MainInventoryRepositoryInterface mainInventoryRepositoryInterface) {
    this.mainInventoryRepositoryInterface = mainInventoryRepositoryInterface;
  }

  @Transactional
  public MainInventory saveInventory(MainInventoryRegisterCommand mainInventoryRegisterCommand) {

    MainInventory mainInventory = toDomainEntity(mainInventoryRegisterCommand);

    try {
      return mainInventoryRepositoryInterface.save(mainInventory);
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
}
