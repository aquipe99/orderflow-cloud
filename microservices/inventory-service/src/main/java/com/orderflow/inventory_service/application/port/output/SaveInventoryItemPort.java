package com.orderflow.inventory_service.application.port.output;

import com.orderflow.inventory_service.domain.model.InventoryItem;

public interface SaveInventoryItemPort {
    InventoryItem save(
            InventoryItem inventoryItem
    );
}
