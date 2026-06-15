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
        .findByProductId(productId);

    return listProductPackaging;
  }

  public ProductPackaging getById(Long id) {
    ProductPackaging productPackaging = productPackagingRepositoryInterface
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Product Packaging Service: Packaging not found."));

    return productPackaging;
  }

  public List<ProductPackaging> getAllById(List<Long> listId) {
    return productPackagingRepositoryInterface.findAllById(listId);
  }
}
