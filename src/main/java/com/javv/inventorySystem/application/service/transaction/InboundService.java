package com.javv.inventorySystem.application.service.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javv.inventorySystem.application.service.mainInventory.MainInventoryService;
import com.javv.inventorySystem.application.service.product.ProductService;
import com.javv.inventorySystem.application.service.productPackaging.ProductPackagingService;
import com.javv.inventorySystem.application.service.supplier.SupplierService;
import com.javv.inventorySystem.application.service.user.UserService;
import com.javv.inventorySystem.domain.repository.InboundRepositoryInterface;

@Service
@Transactional(readOnly = true)
public class InboundService {

  @Autowired
  private UserService userService;

  @Autowired
  private ProductService productService;

  @Autowired
  private SupplierService supplierService;

  @Autowired
  private ProductPackagingService productPackagingService;

  @Autowired
  private MainInventoryService mainInventoryService;

  @Autowired
  private InboundRepositoryInterface inboundRepositoryInterface;

  // @Transactional
  // public void processInbound(InboundRegisterCommand inboundRegisterCommand) {
  //
  // // int supplierId = supplierService
  // // .getById(inboundRegisterCommand.supplierId())
  // // .getId();
  //
  // // UUID encoderId = userService
  // // .getUserById(inboundRegisterCommand.encoderId())
  // // .getId();
  //
  // List<Long> listPackagingId =
  // inboundRegisterCommand.listInboundItem().stream()
  // .map(item -> item.packagingId())
  // .toList();
  //
  // List<Long> listProductId = inboundRegisterCommand.listInboundItem().stream()
  // .map(item -> item.productId())
  // .toList();
  //
  // Map<Long, Product> productMap = productService.get(listProductId)
  // .stream()
  // .collect(Collectors.toMap(
  // entity -> entity.getId(),
  // entity -> entity));
  //
  // Map<Long, ProductPackaging> packagingMap =
  // productPackagingService.getAllById(listPackagingId).stream()
  // .collect(Collectors.toMap(
  // entity -> entity.getId(),
  // entity -> entity));
  //
  // Inbound inbound = new Inbound();
  //
  // // inbound.setSupplierId(supplierId);
  // // inbound.setEncoderId(encoderId);
  // inbound.setInvoiceNumber(inboundRegisterCommand.invoiceNumber());
  // inbound.setDateReceived(inboundRegisterCommand.dateReceived());
  //
  // for (InboundItemRegisterCommand item :
  // inboundRegisterCommand.listInboundItem()) {
  //
  // Product product = productMap.get(item.productId());
  // ProductPackaging packaging = packagingMap.get(item.packagingId());
  //
  // if (product == null) {
  // throw new ResourceNotFoundException(
  // "Inbound Process Fail: Product not found for ID: " + item.productId());
  // }
  //
  // if (packaging == null) {
  // throw new ResourceNotFoundException(
  // "Inbound Process Fail: Product Packaging not found for ID: " +
  // item.packagingId());
  // }
  //
  // int baseQuantityEquivalent =
  // packaging.calculateBaseQuantity(item.quantityReceived());
  //
  // inbound.addInboundItem(
  // product.getSku(),
  // packaging.getId(),
  // item.quantityReceived(),
  // baseQuantityEquivalent);
  // }
  //
  // Inbound savedInbound = inboundRepositoryInterface.save(inbound);
  //
  // for (InboundItem item : savedInbound.getListInboundItem()) {
  // mainInventoryService.incrementStock(item.getProductSku(),
  // item.getBaseQuantityEquivalent());
  // }
  // }
}
