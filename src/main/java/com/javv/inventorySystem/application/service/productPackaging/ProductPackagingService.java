package com.javv.inventorySystem.application.service.productPackaging;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javv.inventorySystem.domain.exception.ResourceNotFoundException;
import com.javv.inventorySystem.domain.model.product.ProductPackaging;
import com.javv.inventorySystem.domain.repository.ProductPackagingRepositoryInterface;

@Service
@Transactional(readOnly = true)
public class ProductPackagingService {

  @Autowired
  private ProductPackagingRepositoryInterface productPackagingRepositoryInterface;

  public ProductPackaging getByPackagingCode(String packagingCode) {
    ProductPackaging productPackaging = productPackagingRepositoryInterface
        .findByPackagingCode(packagingCode)
        .orElseThrow(() -> new ResourceNotFoundException(
            "Product Packaging does not exist with packaging code: " + packagingCode + "."));

    return productPackaging;
  }

  public List<ProductPackaging> getByProductSku(String productSku) {
    List<ProductPackaging> listProductPackaging = productPackagingRepositoryInterface
        .findByProductSku(productSku);

    return listProductPackaging;
  }

  public boolean doAllPackagingCodeExists(List<String> packagingCodes) {
    if (packagingCodes == null || packagingCodes.isEmpty()) {
      throw new IllegalArgumentException(
          "Cannot retrieve list of records with a null or empty list of packaging codes.");
    }

    List<String> filteredList = packagingCodes.stream().distinct().toList();

    List<String> existingList = productPackagingRepositoryInterface
        .findExistingPackagingCodes(filteredList);

    return filteredList.size() == existingList.size();
  }

  public boolean checkIfExistsByPackagingCode(String packagingCode) {
    return productPackagingRepositoryInterface.existsByPackagingCode(packagingCode);
  }

  public List<ProductPackaging> getAllByPackagingCodes(List<String> packagingCodes) {
    return productPackagingRepositoryInterface.findAllByPackagingCode(packagingCodes);
  }

}
