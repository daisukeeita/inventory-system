package com.javv.inventorySystem.infrastructure.persistence.transaction.inbound;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.javv.inventorySystem.infrastructure.persistence.product.ProductJpaEntity;
import com.javv.inventorySystem.infrastructure.persistence.productPackaging.ProductPackagingJpaEntity;
import com.javv.inventorySystem.infrastructure.persistence.supplier.SupplierJpaEntity;
import com.javv.inventorySystem.infrastructure.persistence.user.UserJpaEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "inbound")
@EntityListeners(AuditingEntityListener.class)
public class InboundJpaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "supplier_id", referencedColumnName = "id", nullable = false)
  private SupplierJpaEntity supplierJpaEntity;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "encoder_id", referencedColumnName = "id", nullable = false)
  private UserJpaEntity userJpaEntity;

  @Column(name = "invoice_number", nullable = false)
  private String invoiceNumber;

  @Column(name = "date_received", nullable = false)
  private LocalDateTime dateReceived;

  @OneToMany(mappedBy = "inboudJpaEntity", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<InboundItemJpaEntity> listInboundItem = new ArrayList<>();

  @CreatedDate
  @Column(name = "created_at", updatable = false, nullable = false)
  private Instant createdAt;

  @LastModifiedDate
  @Column(name = "updated_at", nullable = false)
  private Instant updatedAt;

  public InboundJpaEntity() {
  }

  public InboundJpaEntity(
      Long id,
      SupplierJpaEntity supplierJpaEntity,
      UserJpaEntity encoder,
      String invoiceNumber,
      LocalDateTime dateReceived,
      List<InboundItemJpaEntity> listInboundItem) {
    this.id = id;
    this.supplierJpaEntity = supplierJpaEntity;
    this.userJpaEntity = encoder;
    this.dateReceived = dateReceived;
    this.listInboundItem = listInboundItem;
  }

  public void addItem(
      ProductJpaEntity productJpaEntity,
      ProductPackagingJpaEntity productPackagingJpaEntity,
      int quantityReceived,
      int baseQuantityEquivalent) {

    InboundItemJpaEntity inboundItemJpaEntity = new InboundItemJpaEntity();

    inboundItemJpaEntity.setInbound(this);
    inboundItemJpaEntity.setProduct(productJpaEntity);
    inboundItemJpaEntity.setProductPackaging(productPackagingJpaEntity);
    inboundItemJpaEntity.setQuantityReceived(quantityReceived);
    inboundItemJpaEntity.setBaseQuantityEquivalent(baseQuantityEquivalent);

    this.listInboundItem.add(inboundItemJpaEntity);
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setSupplierJpaEntity(SupplierJpaEntity supplierJpaEntity) {
    this.supplierJpaEntity = supplierJpaEntity;
  }

  public void setUserJpaEntity(UserJpaEntity encoder) {
    this.userJpaEntity = encoder;
  }

  public void setInvoiceNumber(String invoiceNumber) {
    this.invoiceNumber = invoiceNumber;
  }

  public void setDateReceived(LocalDateTime dateReceived) {
    this.dateReceived = dateReceived;
  }

  public void setListInboundItem(List<InboundItemJpaEntity> listInboundItem) {
    this.listInboundItem = listInboundItem;
  }

  protected void setCreatedAt(Instant createdAt) {
    this.createdAt = createdAt;
  }

  protected void setUpdatedAt(Instant updatedAt) {
    this.updatedAt = updatedAt;
  }

  public Long getId() {
    return id;
  }

  public UserJpaEntity getUserJpaEntity() {
    return userJpaEntity;
  }

  public SupplierJpaEntity getSupplierJpaEntity() {
    return supplierJpaEntity;
  }

  public String getInvoiceNumber() {
    return invoiceNumber;
  }

  public LocalDateTime getDateReceived() {
    return dateReceived;
  }

  public List<InboundItemJpaEntity> getListInboundItem() {
    return listInboundItem;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }
}
