package com.javv.inventorySystem.infrastructure.persistence.product;

import com.javv.inventorySystem.infrastructure.persistence.supplier.SupplierJpaEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class ProductJpaEntity {
  @Id
  @Column(name = "sku", nullable = false)
  private String sku;

  @Column(name = "name", nullable = false)
  private String name;

  @ManyToOne
  @JoinColumn(name = "supplier_id")
  private SupplierJpaEntity supplierJpaEntity;
}
