package com.orderflow.order_service.infrastructure.messaging.kafka.producer;

import com.orderflow.order_service.application.event.OrderCreatedEvent;
import com.orderflow.order_service.application.port.output.PublishOrderCreatedEventPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
@Slf4j
@Component
public class KafkaOrderEventPublisher implements PublishOrderCreatedEventPort {
    @Value("${orderflow.kafka.topics.order-created}")
    private String orderCreatedTopic;


    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    public KafkaOrderEventPublisher(KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publish(OrderCreatedEvent event) {
        log.info(
                "Publishing OrderCreated event. orderId={}",
                event.orderId()
        );
        kafkaTemplate.send(
                orderCreatedTopic,
                event.orderId().toString(),
                event
        );
        log.info(
                "OrderCreated event sent to topic {}",
                orderCreatedTopic
        );
    }
}
