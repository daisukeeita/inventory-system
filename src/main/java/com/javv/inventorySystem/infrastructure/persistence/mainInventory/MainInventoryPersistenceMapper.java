package com.javv.inventorySystem.infrastructure.persistence.mainInventory;

import org.springframework.stereotype.Component;

import com.javv.inventorySystem.domain.model.mainInventory.MainInventory;
import com.javv.inventorySystem.domain.model.product.Product;
import com.javv.inventorySystem.infrastructure.persistence.product.ProductJpaEntity;
import com.javv.inventorySystem.infrastructure.persistence.product.ProductPersistenceMapper;

@Component
public class MainInventoryPersistenceMapper {

  private ProductPersistenceMapper productPersistenceMapper;

  public MainInventoryPersistenceMapper(ProductPersistenceMapper productPersistenceMapper) {
    this.productPersistenceMapper = productPersistenceMapper;
  }

  public MainInventoryJpaEntity toJpaEntity(MainInventory mainInventory) {

    ProductJpaEntity productJpaEntity = productPersistenceMapper.toJpaEntity(mainInventory.getProductSku());

    MainInventoryJpaEntity mainInventoryJpaEntity = new MainInventoryJpaEntity();
    mainInventoryJpaEntity.setId(mainInventory.getId());
    mainInventoryJpaEntity.setProduct(productJpaEntity);
    mainInventoryJpaEntity.setQuantityOnHand(mainInventory.getQuantityOnHand());
    mainInventoryJpaEntity.setReorderLevel(mainInventory.getReorderLevel());

    return mainInventoryJpaEntity;
  }

  public MainInventory toDomainEntity(MainInventoryJpaEntity mainInventoryJpaEntity) {

    Product product = productPersistenceMapper.toDomainEntity(mainInventoryJpaEntity.getProduct());

    MainInventory mainInventory = new MainInventory();
    mainInventory.setId(mainInventoryJpaEntity.getId());
    mainInventory.setProduct(product);
    mainInventory.setQuantityOnHand(mainInventoryJpaEntity.getQuantityOnHand());
    mainInventory.setReorderLevel(mainInventoryJpaEntity.getReorderLevel());
    mainInventory.setCreatedAt(mainInventoryJpaEntity.getCreatedAt());
    mainInventory.setUpdatedAt(mainInventoryJpaEntity.getUpdatedAt());

    return mainInventory;
  }

  public MainInventoryJpaEntity updateJpaEntity(
      MainInventory mainInventory, MainInventoryJpaEntity mainInventoryJpaEntity) {

    mainInventoryJpaEntity.setQuantityOnHand(mainInventory.getQuantityOnHand());

    return mainInventoryJpaEntity;
  }
}
