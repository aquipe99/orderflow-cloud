package com.orderflow.inventory_service.application.port.input;

import java.util.UUID;

public interface ReserveInventoryUseCase {
    void reserve(
            UUID orderId,
            String productCode,
            Integer quantity
    );
}
