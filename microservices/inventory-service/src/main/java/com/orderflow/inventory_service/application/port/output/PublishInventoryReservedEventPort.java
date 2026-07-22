package com.orderflow.inventory_service.application.port.output;

import com.orderflow.inventory_service.application.event.InventoryReservedEvent;

public interface PublishInventoryReservedEventPort {
    void publish(InventoryReservedEvent event);

}
