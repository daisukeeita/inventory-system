package com.javv.inventorySystem.application.command.productPackaging;

import java.math.BigDecimal;
import java.util.Objects;

public record ProductPackagingResponseRead(
    Long id,
    String packagingCode,
    String sku,
    String unitOfMeasure,
    int conversionFactor,
    BigDecimal price) {

  public ProductPackagingResponseRead {

    Objects.requireNonNull(id,
        "Product Packaging Response Read: ID cannot be null.");

    Objects.requireNonNull(packagingCode,
        "Product Packaging Response Read: Packaging Code cannot be null.");

    Objects.requireNonNull(sku,
        "Product Packaging Response Read: Product SKU cannot be null.");

    Objects.requireNonNull(unitOfMeasure,
        "Product Packaging Response Read: Unit of Measure cannot be null.");

    Objects.requireNonNull(conversionFactor,
        "Product Packaging Response Read: Conversion Factor cannot be null.");

    Objects.requireNonNull(price,
        "Product Packaging Response Read: Price cannot be null.");
  }
}
