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
        log.info("Order Id : {}", event.orderId());

        throw new RuntimeException("Simulating email server failure");
    }
}
