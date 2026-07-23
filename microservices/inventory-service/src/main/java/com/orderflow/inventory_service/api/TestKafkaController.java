package com.orderflow.inventory_service.api;


import com.orderflow.inventory_service.application.event.OrderCreatedEvent;
import com.orderflow.inventory_service.application.event.OrderItemEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RestController
public class TestKafkaController {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public TestKafkaController(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/test/duplicate")
    public String duplicate() {

        UUID eventId =
                UUID.fromString("11111111-1111-1111-1111-111111111111");

        UUID orderId =
                UUID.fromString("22222222-2222-2222-2222-222222222222");

        OrderCreatedEvent event =
                new OrderCreatedEvent(
                        eventId,
                        orderId,
                        1L,
                        List.of(
                                new OrderItemEvent(
                                        "LAPTOP-001",
                                        2
                                )
                        ),
                        BigDecimal.valueOf(1000),
                        Instant.now()
                );

        kafkaTemplate.send(
                "orders.created",
                orderId.toString(),
                event
        );

        return "Event sent";
    }
}