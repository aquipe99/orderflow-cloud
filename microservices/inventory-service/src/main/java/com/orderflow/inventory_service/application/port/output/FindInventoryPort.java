package com.orderflow.inventory_service.application.port.output;

import com.orderflow.inventory_service.domain.model.Inventory;

import java.util.Optional;

public interface FindInventoryPort {
    Optional<Inventory> findByProductCode(String productCode);
}
