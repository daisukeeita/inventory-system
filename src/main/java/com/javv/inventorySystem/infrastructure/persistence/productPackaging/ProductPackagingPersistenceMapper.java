package com.javv.inventorySystem.infrastructure.persistence.productPackaging;

import org.springframework.stereotype.Component;

import com.javv.inventorySystem.domain.model.product.ProductPackaging;
import com.javv.inventorySystem.infrastructure.persistence.product.ProductJpaEntity;
import com.javv.inventorySystem.infrastructure.persistence.unitsOfMeasure.UnitsOfMeasureJpaEntity;

@Component
public class ProductPackagingPersistenceMapper {

  public ProductPackaging toDomainEntity(ProductPackagingJpaEntity productPackagingJpaEntity) {

    ProductPackaging productPackaging = new ProductPackaging();
    productPackaging.setId(productPackagingJpaEntity.getId());
    productPackaging.setPackagingCode(productPackagingJpaEntity.getPackagingCode());
    productPackaging.setProductSku(productPackagingJpaEntity.getProduct().getSku());
    productPackaging.setUnitsOfMeasureName(productPackagingJpaEntity.getUnitsOfMeasure().getName());
    productPackaging.setConversionFactor(productPackagingJpaEntity.getConversionFactor());
    productPackaging.setPrice(productPackagingJpaEntity.getPrice());

    return productPackaging;

  }

  public ProductPackagingJpaEntity toJpaEntity(
      ProductPackaging productPackaging,
      ProductJpaEntity productJpaEntity,
      UnitsOfMeasureJpaEntity unitsOfMeasureJpaEntity) {

    ProductPackagingJpaEntity productPackagingJpaEntity = new ProductPackagingJpaEntity();
    productPackagingJpaEntity.setId(productPackaging.getId());
    productPackagingJpaEntity.setPackagingCode(productPackaging.getPackagingCode());
    productPackagingJpaEntity.setProduct(productJpaEntity);
    productPackagingJpaEntity.setUnitsOfMeasure(unitsOfMeasureJpaEntity);
    productPackagingJpaEntity.setConversionFactor(productPackaging.getConversionFactor());
    productPackagingJpaEntity.setPrice(productPackaging.getPrice());

    return productPackagingJpaEntity;
  }
}
