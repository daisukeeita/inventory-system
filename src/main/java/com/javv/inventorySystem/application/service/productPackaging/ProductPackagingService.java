package com.javv.inventorySystem.application.service.productPackaging;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javv.inventorySystem.domain.model.product.ProductPackaging;
import com.javv.inventorySystem.domain.repository.ProductPackagingRepositoryInterface;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional(readOnly = true)
public class ProductPackagingService {

  @Autowired
  private ProductPackagingRepositoryInterface productPackagingRepositoryInterface;

  public List<ProductPackaging> getByProductId(Long productId) {
    List<ProductPackaging> listProductPackaging = productPackagingRepositoryInterface
        .getByProductId(productId);

    return listProductPackaging;
  }

  public ProductPackaging getById(Long id) {
    ProductPackaging productPackaging = productPackagingRepositoryInterface
        .getById(id)
        .orElseThrow(() -> new EntityNotFoundException("Product Packaging Service: Packaging not found."));

    return productPackaging;
  }
}
