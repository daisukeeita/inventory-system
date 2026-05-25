package com.javv.inventorySystem.domain.repository;

import java.util.Optional;

import com.javv.inventorySystem.domain.model.inventory.MainInventory;

public interface MainInventoryRepositoryInterface {

  MainInventory save(MainInventory mainInventory);

  MainInventory update(MainInventory mainInventory);

  Optional<MainInventory> getById(Integer id);

  Optional<MainInventory> getBySku(String sku);
}
