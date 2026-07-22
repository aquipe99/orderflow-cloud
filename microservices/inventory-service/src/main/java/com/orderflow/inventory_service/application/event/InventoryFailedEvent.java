package com.orderflow.inventory_service.application.event;

import java.time.Instant;
import java.util.UUID;

public record InventoryFailedEvent(
        UUID orderId,

        String productCode,

        String reason,

        Instant failedAt
) {
}
