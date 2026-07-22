package com.orderflow.notification_service.application.service;

import com.orderflow.notification_service.application.event.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationApplicationService {
    public void process(OrderCreatedEvent event) {

        log.info("===============================================");
        log.info("NEW ORDER RECEIVED");
        log.info("Order Id     : {}", event.orderId());
        log.info("Customer Id  : {}", event.customerId());
        log.info("Total        : {}", event.total());
        log.info("Created At   : {}", event.createdAt());

        log.info("Items:");

        event.items().forEach(item ->
                log.info("   Product: {} | Quantity: {}",
                        item.productCode(),
                        item.quantity())
        );

        throw new RuntimeException("Simulating email server failure");
    }
}
