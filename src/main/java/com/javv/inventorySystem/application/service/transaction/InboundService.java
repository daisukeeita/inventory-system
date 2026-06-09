package com.javv.inventorySystem.application.service.transaction;

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

    Inbound inbound = new Inbound();

    inbound.setSupplierId(inboundRegisterCommand.supplierId());
    inbound.setEncoderId(inboundRegisterCommand.encoderId());
    inbound.setInvoiceNumber(inboundRegisterCommand.invoiceNumber());
    inbound.setDateReceived(inboundRegisterCommand.dateReceived());

    for (InboundItemRegisterCommand item : inboundRegisterCommand.listInboundItem()) {

      ProductPackaging packaging = productPackagingService.getById(item.packagingId());
      int baseQuantityEquivalent = packaging.calculateBaseQuantity(item.quantityReceived());

      mainInventoryService.addStockInventory(item.productId(), baseQuantityEquivalent);

      inbound.addInboundItem(
          item.productId(),
          item.packagingId(),
          item.quantityReceived(),
          baseQuantityEquivalent);

    }

    inboundRepositoryInterface.save(inbound);
  }
}
