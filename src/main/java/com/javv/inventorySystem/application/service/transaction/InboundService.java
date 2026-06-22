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
import com.javv.inventorySystem.domain.exception.ServiceOperationException;
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

    String supplierCode = inboundRegisterCommand.supplierCode();
    String username = inboundRegisterCommand.encoderUsername();

    List<String> listProductSku = inboundRegisterCommand.listInboundItem()
        .stream()
        .map(inboundItem -> inboundItem.productSku())
        .toList();

    List<String> listPackagingCode = inboundRegisterCommand.listInboundItem()
        .stream()
        .map(inboundItem -> inboundItem.packagingCode())
        .toList();

    if (!supplierService
        .checkIfExistsBySupplierCode(supplierCode)) {
      throw new ServiceOperationException(
          "Processing Inbound Failed: Supplier does not exists with supplier code: " +
              supplierCode + ".");
    }

    if (!userService.checkIfExistsByUsername(username)) {
      throw new ServiceOperationException(
          "Processing Inbound Failed: Encoder does not exits with username: " + username + ".");
    }

    if (!productService.doAllSkuExists(listProductSku)) {
      throw new ServiceOperationException(
          "Processing Inbound Failed: One or more product does not exist from the provided list of SKU.");
    }

    if (!productPackagingService.doAllPackagingCodeExists(listPackagingCode)) {
      throw new ServiceOperationException(
          "Processing Inbound Failed: One or more product packaging does not exists from the provided list of packaging codes.");
    }

    Map<String, ProductPackaging> mappedPackaging = productPackagingService
        .getAllByPackagingCodes(listPackagingCode)
        .stream()
        .collect(Collectors.toMap(
            productPackaging -> productPackaging.getPackagingCode(),
            productPackaging -> productPackaging));

    Inbound inbound = new Inbound();

    inbound.setSupplierCode(supplierCode);
    inbound.setEncoderUsername(username);
    inbound.setInvoiceNumber(inboundRegisterCommand.invoiceNumber());
    inbound.setDateReceived(inboundRegisterCommand.dateReceived());

    for (InboundItemRegisterCommand inboundItemCommand : inboundRegisterCommand.listInboundItem()) {

      ProductPackaging productPackaging = mappedPackaging.get(inboundItemCommand.packagingCode());

      int baseQuantityEquivalent = productPackaging
          .calculateBaseQuantity(inboundItemCommand.quantityReceived());

      inbound.addInboundItem(
          inboundItemCommand.productSku(),
          inboundItemCommand.packagingCode(),
          inboundItemCommand.quantityReceived(),
          baseQuantityEquivalent);
    }

    Inbound savedInbound = inboundRepositoryInterface.save(inbound);

    for (InboundItem inboundItem : savedInbound.getListInboundItem()) {
      mainInventoryService.incrementStock(
          inboundItem.getProductSku(),
          inboundItem.getBaseQuantityEquivalent());
    }
  }
}
