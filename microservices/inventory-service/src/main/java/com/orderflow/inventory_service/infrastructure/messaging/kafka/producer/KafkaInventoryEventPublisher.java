package com.orderflow.inventory_service.infrastructure.messaging.kafka.producer;

import com.orderflow.inventory_service.application.event.InventoryReservedEvent;
import com.orderflow.inventory_service.application.port.output.PublishInventoryReservedEventPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaInventoryEventPublisher
        implements PublishInventoryReservedEventPort {

    @Value("${orderflow.kafka.topics.inventory-reserved}")
    private String TOPIC;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaInventoryEventPublisher(
            KafkaTemplate<String, Object> kafkaTemplate) {

        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publish(InventoryReservedEvent event) {

        kafkaTemplate.send(
                TOPIC,
                event.orderId().toString(),
                event
        );

        log.info(
                "InventoryReservedEvent published. orderId={}",
                event.orderId()
        );
    }
}
