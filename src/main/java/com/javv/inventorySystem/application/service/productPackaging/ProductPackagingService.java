package com.javv.inventorySystem.application.service.productPackaging;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javv.inventorySystem.domain.model.product.ProductPackaging;
import com.javv.inventorySystem.domain.repository.ProductPackagingRepositoryInterface;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional(readOnly = true)
public class ProductPackagingService {

  private final ProductPackagingRepositoryInterface productPackagingRepositoryInterface;

  public ProductPackagingService(
      ProductPackagingRepositoryInterface productPackagingRepositoryInterface) {
    this.productPackagingRepositoryInterface = productPackagingRepositoryInterface;
  }

  public List<ProductPackaging> getByProductId(Long productId) {
    return productPackagingRepositoryInterface.getByProductId(productId);
  }

  public ProductPackaging getById(int id) {
    ProductPackaging productPackaging = productPackagingRepositoryInterface
        .getById(id)
        .orElseThrow(() -> new EntityNotFoundException("Product Packaging Service: Packaging not found."));

    return productPackaging;
  }
}
