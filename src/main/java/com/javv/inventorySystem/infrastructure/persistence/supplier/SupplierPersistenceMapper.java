package com.javv.inventorySystem.infrastructure.persistence.supplier;

import org.springframework.stereotype.Component;

import com.javv.inventorySystem.domain.model.supplier.Supplier;
import com.javv.inventorySystem.domain.model.supplier.SupplierAddress;

@Component
public class SupplierPersistenceMapper {

  public SupplierJpaEntity toJpaEntity(Supplier supplier) {

    SupplierJpaEntity supplierJpaEntity = new SupplierJpaEntity();
    supplierJpaEntity.setId(supplier.getId());
    supplierJpaEntity.setSupplierCode(supplier.getSupplierCode());
    supplierJpaEntity.setCompanyName(supplier.getCompanyName());
    supplierJpaEntity.setContactName(supplier.getContactName());
    supplierJpaEntity.setPhoneNumber(supplier.getPhoneNumber());
    supplierJpaEntity.setEmail(supplier.getEmail());

    SupplierAddressJpaEntity supplierAddressJpaEntity = new SupplierAddressJpaEntity();
    supplierAddressJpaEntity.setCity(supplier.getSupplierAddress().getCity());
    supplierAddressJpaEntity.setCountry(supplier.getSupplierAddress().getCountry());
    supplierAddressJpaEntity.setPostalCode(supplier.getSupplierAddress().getPostalCode());
    supplierAddressJpaEntity.setState(supplier.getSupplierAddress().getState());
    supplierAddressJpaEntity.setStreet(supplier.getSupplierAddress().getStreet());

    supplierAddressJpaEntity.setSupplier(supplierJpaEntity);
    supplierJpaEntity.setSupplierAddress(supplierAddressJpaEntity);

    return supplierJpaEntity;
  }

  public Supplier toDomainEntity(SupplierJpaEntity supplierJpaEntity) {

    SupplierAddress supplierAddress = new SupplierAddress();
    supplierAddress.setSupplierId(supplierJpaEntity.getSupplierAddressJpaEntity().getSupplierId());
    supplierAddress.setStreet(supplierJpaEntity.getSupplierAddressJpaEntity().getStreet());
    supplierAddress.setCity(supplierJpaEntity.getSupplierAddressJpaEntity().getCity());
    supplierAddress.setState(supplierJpaEntity.getSupplierAddressJpaEntity().getState());
    supplierAddress.setCountry(supplierJpaEntity.getSupplierAddressJpaEntity().getCountry());
    supplierAddress.setPostalCode(supplierJpaEntity.getSupplierAddressJpaEntity().getPostalCode());

    Supplier supplier = new Supplier();
    supplier.setId(supplierJpaEntity.getId());
    supplier.setSupplierCode(supplierJpaEntity.getSupplierCode());
    supplier.setCompanyName(supplierJpaEntity.getCompanyName());
    supplier.setContactName(supplierJpaEntity.getContactName());
    supplier.setPhoneNumber(supplierJpaEntity.getPhoneNumber());
    supplier.setEmail(supplierJpaEntity.getEmail());
    supplier.setSupplierAddress(supplierAddress);

    return supplier;
  }

  public SupplierJpaEntity updateEntity(Supplier supplier, SupplierJpaEntity supplierJpaEntity) {

    SupplierAddress supplierAddress = supplier.getSupplierAddress();

    supplierJpaEntity.setCompanyName(supplier.getCompanyName());
    supplierJpaEntity.setContactName(supplier.getContactName());
    supplierJpaEntity.setPhoneNumber(supplier.getPhoneNumber());
    supplierJpaEntity.setEmail(supplier.getEmail());

    if (supplierAddress != null) {
      supplierJpaEntity.updateAddress(
          supplierAddress.getStreet(),
          supplierAddress.getCity(),
          supplierAddress.getState(),
          supplierAddress.getPostalCode(),
          supplierAddress.getCountry());
    }

    return supplierJpaEntity;

  }
}
