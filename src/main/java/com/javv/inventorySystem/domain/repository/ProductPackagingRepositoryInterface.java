package com.javv.inventorySystem.domain.repository;

import java.util.List;
import java.util.Optional;

import com.javv.inventorySystem.domain.model.product.ProductPackaging;

public interface ProductPackagingRepositoryInterface {

  List<ProductPackaging> getByProductId(Long productId);

  Optional<ProductPackaging> getById(Long id);
}
