package com.orderflow.order_service.application.event;

import java.time.Instant;
import java.util.UUID;

public record InventoryReservedEvent(
        UUID orderId,
        Instant reservedAt
) {

}
