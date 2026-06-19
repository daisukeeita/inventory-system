package com.javv.inventorySystem.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.javv.inventorySystem.domain.model.product.Product;

public interface ProductRepositoryInterface {
  Product save(Product product);

  Product update(Product product);

  Optional<Product> findBySku(String sku);

  boolean existsBySku(String sku);

  List<String> findExistingSkus(List<String> sku);

  Page<Product> findAll(Pageable pageable);
}
