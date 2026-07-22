package com.orderflow.inventory_service.application.service;


import com.orderflow.inventory_service.application.event.InventoryReservedEvent;
import com.orderflow.inventory_service.application.port.input.ReserveInventoryUseCase;
import com.orderflow.inventory_service.application.port.output.FindInventoryItemPort;
import com.orderflow.inventory_service.application.port.output.PublishInventoryReservedEventPort;
import com.orderflow.inventory_service.application.port.output.SaveInventoryItemPort;
import com.orderflow.inventory_service.domain.model.InventoryItem;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;


@Service
public class InventoryApplicationService implements ReserveInventoryUseCase {

    private final FindInventoryItemPort findInventoryItemPort;
    private final SaveInventoryItemPort saveInventoryItemPort;
    private final PublishInventoryReservedEventPort publishInventoryReservedEventPort;

    public InventoryApplicationService(FindInventoryItemPort findInventoryItemPort, SaveInventoryItemPort saveInventoryItemPort, PublishInventoryReservedEventPort publishInventoryReservedEventPort) {
        this.findInventoryItemPort = findInventoryItemPort;
        this.saveInventoryItemPort = saveInventoryItemPort;
        this.publishInventoryReservedEventPort = publishInventoryReservedEventPort;
    }

    @Override
    public void reserve(UUID orderId, String productCode, Integer quantity) {
        InventoryItem item =
                findInventoryItemPort.findByProductCode(productCode)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Inventory item not found"
                                )
                        );


        item.reserveStock(quantity);


        saveInventoryItemPort.save(item);

        InventoryReservedEvent event =
                new InventoryReservedEvent(
                        orderId,
                        Instant.now()
                );

        publishInventoryReservedEventPort.publish(event);


    }
}
