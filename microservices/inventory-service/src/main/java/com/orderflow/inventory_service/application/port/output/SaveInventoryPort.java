package com.orderflow.inventory_service.application.port.output;

import com.orderflow.inventory_service.domain.model.Inventory;

public interface SaveInventoryPort {
    Inventory save(Inventory inventory);
}
