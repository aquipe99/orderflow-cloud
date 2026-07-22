package com.orderflow.inventory_service.application.event;

public record OrderItemEvent(
        String productCode,
        Integer quantity
) {
}
