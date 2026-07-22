package com.orderflow.order_service.application.event;

public record OrderItemEvent(
        String productCode,
        Integer quantity
) {

}
