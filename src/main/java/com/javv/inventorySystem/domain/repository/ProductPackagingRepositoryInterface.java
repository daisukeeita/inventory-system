package com.javv.inventorySystem.domain.repository;

import java.util.List;
import java.util.Optional;

import com.javv.inventorySystem.domain.model.product.ProductPackaging;

public interface ProductPackagingRepositoryInterface {

  List<ProductPackaging> findByProductId(Long productId);

  Optional<ProductPackaging> findById(Long id);

  List<ProductPackaging> findAllById(List<Long> listId);
}
