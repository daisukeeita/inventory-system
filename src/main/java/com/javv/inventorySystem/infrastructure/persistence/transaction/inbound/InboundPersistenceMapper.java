package com.javv.inventorySystem.infrastructure.persistence.transaction.inbound;

import org.springframework.stereotype.Component;

import com.javv.inventorySystem.domain.model.transaction.inbound.Inbound;
import com.javv.inventorySystem.infrastructure.persistence.product.ProductPersistenceMapper;
import com.javv.inventorySystem.infrastructure.persistence.productPackaging.ProductPackagingPersistenceMapper;
import com.javv.inventorySystem.infrastructure.persistence.supplier.SupplierPersistenceMapper;
import com.javv.inventorySystem.infrastructure.persistence.user.UserPersistenceMapper;

@Component
public class InboundPersistenceMapper {

  private UserPersistenceMapper userPersistenceMapper;
  private ProductPersistenceMapper productPersistenceMapper;
  private SupplierPersistenceMapper supplierPersistenceMapper;
  private ProductPackagingPersistenceMapper productPackagingPersistenceMapper;

  public InboundPersistenceMapper(
      UserPersistenceMapper userPersistenceMapper,
      ProductPersistenceMapper productPersistenceMapper,
      SupplierPersistenceMapper supplierPersistenceMapper,
      ProductPackagingPersistenceMapper productPackagingPersistenceMapper) {
    this.userPersistenceMapper = userPersistenceMapper;
    this.productPersistenceMapper = productPersistenceMapper;
    this.supplierPersistenceMapper = supplierPersistenceMapper;
    this.productPackagingPersistenceMapper = productPackagingPersistenceMapper;
  }

  public InboundJpaEntity toJpaEntity(Inbound inbound) {

    return null;
  }

  public Inbound toDomainEntity(InboundJpaEntity inboundJpaEntity) {
    return null;
  }

  public InboundJpaEntity updateJpaEntity(Inbound inbound, InboundJpaEntity inboundJpaEntity) {
    return null;
  }

}
