package com.javv.inventorySystem.infrastructure.persistence.productPackaging;

import org.springframework.stereotype.Component;

import com.javv.inventorySystem.infrastructure.persistence.product.ProductPersistenceMapper;
import com.javv.inventorySystem.infrastructure.persistence.unitsOfMeasure.UnitsOfMeasurePersistenceMapper;

@Component
public class ProductPackagingPersistenceMapper {

  private UnitsOfMeasurePersistenceMapper unitsOfMeasurePersistenceMapper;
  private ProductPersistenceMapper productPersistenceMapper;

  public ProductPackagingPersistenceMapper(
      ProductPersistenceMapper productPersistenceMapper,
      UnitsOfMeasurePersistenceMapper unitsOfMeasurePersistenceMapper) {
    this.productPersistenceMapper = productPersistenceMapper;
    this.unitsOfMeasurePersistenceMapper = unitsOfMeasurePersistenceMapper;
  }

  // public ProductPackagingJpaEntity toJpaEntity(ProductPackaging
  // productPackaging) {
  //
  // ProductJpaEntity productJpaEntity =
  // productPersistenceMapper.toJpaEntity(productPackaging.getProductId());
  //
  // UnitsOfMeasureJpaEntity unitsOfMeasureJpaEntity =
  // unitsOfMeasurePersistenceMapper
  // .toJpaEntity(productPackaging.getUnitsOfMeasureId());
  //
  // ProductPackagingJpaEntity productPackagingJpaEntity = new
  // ProductPackagingJpaEntity();
  //
  // // productPackagingJpaEntity.setId(productPackaging.getId());
  // productPackagingJpaEntity.setPackagingCode(productPackaging.getPackagingCode());
  // productPackagingJpaEntity.setProduct(productJpaEntity);
  // productPackagingJpaEntity.setUnitsOfMeasure(unitsOfMeasureJpaEntity);
  // productPackagingJpaEntity.setConversionFactor(productPackaging.getConversionFactor());
  // productPackagingJpaEntity.setPrice(productPackaging.getPrice());
  //
  // return productPackagingJpaEntity;
  // }

  // public ProductPackaging toDomainEntity(ProductPackagingJpaEntity
  // productPackagingJpaEntity) {
  //
  // Product product =
  // productPersistenceMapper.toDomainEntity(productPackagingJpaEntity.getProduct());
  //
  // UnitsOfMeasure unitsOfMeasure =
  // unitsOfMeasurePersistenceMapper.toDomainEntity(
  // productPackagingJpaEntity.getUnitsOfMeasure());
  //
  // ProductPackaging productPackaging = new ProductPackaging();
  //
  // productPackaging.setId(productPackagingJpaEntity.getId());
  // productPackaging.setPackagingCode(productPackagingJpaEntity.getPackagingCode());
  // productPackaging.setProduct(product);
  // productPackaging.setUnitsOfMeasure(unitsOfMeasure);
  // productPackaging.setConversionFactor(productPackagingJpaEntity.getConversionFactor());
  // productPackaging.setPrice(productPackagingJpaEntity.getPrice());
  //
  // return productPackaging;
  // }
}
