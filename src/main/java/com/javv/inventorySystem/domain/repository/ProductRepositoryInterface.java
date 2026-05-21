package com.javv.inventorySystem.domain.repository;

import java.util.Optional;

import com.javv.inventorySystem.domain.model.product.Product;

public interface ProductRepositoryInterface {
  Product save(Product product);

  Product update(Product product);

  Optional<Product> getBySku(String sku);

  Optional<Product> getById(Long id);
}
