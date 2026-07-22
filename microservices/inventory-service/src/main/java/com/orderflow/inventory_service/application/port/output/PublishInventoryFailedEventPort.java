package com.orderflow.inventory_service.application.port.output;

import com.orderflow.inventory_service.application.event.InventoryFailedEvent;

public interface PublishInventoryFailedEventPort {
    void publish(InventoryFailedEvent event);
}
