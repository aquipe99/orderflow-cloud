package com.orderflow.order_service.infrastructure.messaging.kafka.consumer;

import com.orderflow.order_service.application.event.InventoryReservedEvent;
import com.orderflow.order_service.application.port.input.UpdateOrderUseCase;
import com.orderflow.order_service.domain.model.OrderStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InventoryReservedConsumer {
    private final UpdateOrderUseCase updateOrderUseCase;

    public InventoryReservedConsumer(UpdateOrderUseCase updateOrderUseCase) {
        this.updateOrderUseCase = updateOrderUseCase;
    }
    @KafkaListener(
            topics = "inventory.reserved",
            groupId = "order-group"
    )
    public void consume(
            InventoryReservedEvent event
    ){

        log.info("===============================");
        log.info("INVENTORY RESERVED RECEIVED");
        log.info(
                "OrderId: {}",
                event.orderId()
        );


        try {

            updateOrderUseCase.updateStatus(
                    event.orderId(),
                    OrderStatus.INVENTORY_RESERVED
            );

        } catch (Exception ex){

            ex.printStackTrace();

            throw ex;
        }


        log.info(
                "Order {} updated to INVENTORY_RESERVED",
                event.orderId()
        );

    }

}
