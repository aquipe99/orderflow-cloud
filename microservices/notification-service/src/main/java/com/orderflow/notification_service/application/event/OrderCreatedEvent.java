package com.orderflow.notification_service.application.event;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record OrderCreatedEvent(
        UUID orderId,

        Long customerId,

        BigDecimal total,

        Instant createdAt
) {
}
