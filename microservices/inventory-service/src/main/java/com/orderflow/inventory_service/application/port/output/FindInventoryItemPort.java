package com.orderflow.inventory_service.application.port.output;

import com.orderflow.inventory_service.domain.model.InventoryItem;

import java.util.Optional;

public interface FindInventoryItemPort {
    Optional<InventoryItem> findByProductCode(
            String productCode
    );
}
