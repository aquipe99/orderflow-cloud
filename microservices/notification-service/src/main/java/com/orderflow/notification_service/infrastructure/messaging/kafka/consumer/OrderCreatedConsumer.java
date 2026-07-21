package com.orderflow.notification_service.infrastructure.messaging.kafka.consumer;

import com.orderflow.notification_service.NotificationServiceApplication;
import com.orderflow.notification_service.application.event.OrderCreatedEvent;
import com.orderflow.notification_service.application.service.NotificationApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderCreatedConsumer {

    private  final NotificationApplicationService notificationServiceApplication;

    public OrderCreatedConsumer(NotificationApplicationService notificationServiceApplication) {
        this.notificationServiceApplication = notificationServiceApplication;
    }

    @KafkaListener(
            topics = "${orderflow.kafka.topics.order-created}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(@Payload OrderCreatedEvent event) {

        log.info("OrderCreated event received. orderId={}", event.orderId());

        notificationServiceApplication.process(event);
    }
}
