package com.javv.inventorySystem.domain.repository;

import java.util.Optional;

import com.javv.inventorySystem.domain.model.product.ProductPackaging;

public interface ProductPackagingRepositoryInterface {

  ProductPackaging save(ProductPackaging productPackaging);

  ProductPackaging update(ProductPackaging productPackaging);

  Optional<ProductPackaging> getByPackagingCode(String packagingCode);

  Optional<ProductPackaging> getById(Integer id);
}
