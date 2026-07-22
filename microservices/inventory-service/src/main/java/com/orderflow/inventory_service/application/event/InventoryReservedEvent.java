package com.orderflow.inventory_service.application.event;

import java.time.Instant;
import java.util.UUID;

public record InventoryReservedEvent(
        UUID orderId,
        Instant reservedAt
) {
}
