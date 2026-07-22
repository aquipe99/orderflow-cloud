package com.orderflow.notification_service.application.event;

public record OrderItemEvent(
        String productCode,
        Integer quantity
) {
}
