package com.orderflow.order_service.application.dto.request;

public record OrderItemRequest(
        String productCode,

        Integer quantity
) {
}
