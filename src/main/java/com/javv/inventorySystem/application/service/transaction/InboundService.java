package com.javv.inventorySystem.application.service.transaction;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javv.inventorySystem.application.command.transaction.inbound.InboundItemRegisterCommand;
import com.javv.inventorySystem.application.command.transaction.inbound.InboundRegisterCommand;
import com.javv.inventorySystem.application.service.mainInventory.MainInventoryService;
import com.javv.inventorySystem.application.service.product.ProductService;
import com.javv.inventorySystem.application.service.productPackaging.ProductPackagingService;
import com.javv.inventorySystem.application.service.supplier.SupplierService;
import com.javv.inventorySystem.application.service.user.UserService;
import com.javv.inventorySystem.domain.model.product.ProductPackaging;
import com.javv.inventorySystem.domain.model.transaction.inbound.Inbound;
import com.javv.inventorySystem.domain.model.transaction.inbound.InboundItem;
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

  @Transactional
  public void processInbound(InboundRegisterCommand inboundRegisterCommand) {

    List<Long> listPackagingId = inboundRegisterCommand.listInboundItem().stream()
        .map(item -> item.packagingId())
        .toList();

    Map<Long, ProductPackaging> packagingMap = productPackagingService.getAllById(listPackagingId).stream()
        .collect(Collectors.toMap(
            entity -> entity.getId(),
            entity -> entity));

    Inbound inbound = new Inbound();

    inbound.setSupplierId(inboundRegisterCommand.supplierId());
    inbound.setEncoderId(inboundRegisterCommand.encoderId());
    inbound.setInvoiceNumber(inboundRegisterCommand.invoiceNumber());
    inbound.setDateReceived(inboundRegisterCommand.dateReceived());

    for (InboundItemRegisterCommand item : inboundRegisterCommand.listInboundItem()) {

      ProductPackaging packaging = packagingMap.get(item.packagingId());
      int baseQuantityEquivalent = packaging.calculateBaseQuantity(item.quantityReceived());

      inbound.addInboundItem(
          item.productId(),
          item.packagingId(),
          item.quantityReceived(),
          baseQuantityEquivalent);

    }

    Inbound savedInbound = inboundRepositoryInterface.save(inbound);

    for (InboundItem item : savedInbound.getListInboundItem()) {
      mainInventoryService.addStockInventory(item.getProductId(), item.getBaseQuantityEquivalent());
    }
  }
}
