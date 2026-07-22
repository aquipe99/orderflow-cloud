package com.orderflow.inventory_service.application.event;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record OrderCreatedEvent(
        UUID orderId,
        Long customerId,
        List<OrderItemEvent> items,
        BigDecimal total,
        Instant createdAt
) {
}
