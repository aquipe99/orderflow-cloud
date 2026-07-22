package com.orderflow.inventory_service.infrastructure.messaging.kafka.consumer;

import com.orderflow.inventory_service.application.event.OrderCreatedEvent;
import com.orderflow.inventory_service.application.event.OrderItemEvent;
import com.orderflow.inventory_service.application.port.input.ReserveInventoryUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderCreatedConsumer {

    private final ReserveInventoryUseCase reserveInventoryUseCase;

    public OrderCreatedConsumer(ReserveInventoryUseCase reserveInventoryUseCase) {
        this.reserveInventoryUseCase = reserveInventoryUseCase;
    }

    @KafkaListener(
            topics = "orders.created",
            groupId = "inventory-group"
    )
    public void consume(OrderCreatedEvent event) {

        log.info("=======================================");
        log.info("ORDER RECEIVED");
        log.info("OrderId: {}", event.orderId());

        for (OrderItemEvent item : event.items()) {

            log.info(
                    "Reserving {} units of {}",
                    item.quantity(),
                    item.productCode()
            );

            reserveInventoryUseCase.reserve(
                    event.orderId(),
                    item.productCode(),
                    item.quantity()
            );
        }

        log.info("Inventory reserved successfully");
    }
}
