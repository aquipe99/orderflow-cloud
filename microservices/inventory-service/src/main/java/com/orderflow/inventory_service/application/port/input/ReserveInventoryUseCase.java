package com.orderflow.inventory_service.application.port.input;

import com.orderflow.inventory_service.application.event.OrderCreatedEvent;

public interface ReserveInventoryUseCase {
    void reserveInventory(OrderCreatedEvent event);
}
