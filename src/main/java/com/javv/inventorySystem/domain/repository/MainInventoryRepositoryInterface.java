package com.javv.inventorySystem.domain.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.javv.inventorySystem.domain.model.mainInventory.MainInventory;

public interface MainInventoryRepositoryInterface {

  MainInventory save(MainInventory mainInventory);

  MainInventory update(MainInventory mainInventory);

  Optional<MainInventory> findById(Integer id);

  Optional<MainInventory> findByProductId(Long productId);

  Page<MainInventory> findAll(Pageable pageable);
}
