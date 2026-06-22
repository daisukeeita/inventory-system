package com.javv.inventorySystem.domain.repository;

import java.util.List;
import java.util.Optional;

import com.javv.inventorySystem.domain.model.product.ProductPackaging;

public interface ProductPackagingRepositoryInterface {

  List<ProductPackaging> findByProductSku(String productSku);

  List<ProductPackaging> findAllByPackagingCode(List<String> packagingCodes);

  Optional<ProductPackaging> findByPackagingCode(String packagingCode);

  boolean existsByPackagingCode(String packagingCode);

  List<String> findExistingPackagingCodes(List<String> packagingCodes);
}
