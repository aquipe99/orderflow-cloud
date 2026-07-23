package com.orderflow.inventory_service.infrastructure.messaging.kafka.consumer;

import com.orderflow.inventory_service.application.event.OrderCreatedEvent;
import com.orderflow.inventory_service.application.event.OrderItemEvent;
import com.orderflow.inventory_service.application.port.input.ReserveInventoryUseCase;
import com.orderflow.inventory_service.application.port.output.ExistsProcessedEventPort;
import com.orderflow.inventory_service.application.port.output.SaveProcessedEventPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderCreatedConsumer {

    private final ReserveInventoryUseCase reserveInventoryUseCase;
    private final ExistsProcessedEventPort existsProcessedEventPort;

    private final SaveProcessedEventPort saveProcessedEventPort;
    public OrderCreatedConsumer(ReserveInventoryUseCase reserveInventoryUseCase, ExistsProcessedEventPort existsProcessedEventPort, SaveProcessedEventPort saveProcessedEventPort) {
        this.reserveInventoryUseCase = reserveInventoryUseCase;
        this.existsProcessedEventPort = existsProcessedEventPort;
        this.saveProcessedEventPort = saveProcessedEventPort;
    }

    @KafkaListener(
            topics = "orders.created",
            groupId = "inventory-group"
    )
    public void consume(OrderCreatedEvent event) {

        if (existsProcessedEventPort.exists(event.eventId())) {

            log.info(
                    "Duplicate event ignored. eventId={}",
                    event.eventId()
            );

            return;
        }
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

            saveProcessedEventPort.save(
                    event.eventId(),
                    "inventory-service"
            );
        }

        log.info("Inventory reserved successfully");
    }
}
