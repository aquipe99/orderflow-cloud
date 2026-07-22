package com.orderflow.order_service.api.dto;

import com.orderflow.order_service.domain.model.Order;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record OrderResponse(
        UUID id,
        Long customerId,
        List<OrderItemResponse> items,
        BigDecimal total,
        String status,
        Instant createdAt
) {
    public static OrderResponse from(Order order) {
        List<OrderItemResponse> items =
                order.getItems()
                        .stream()
                        .map(OrderItemResponse::from)
                        .toList();

        return new OrderResponse(
                order.getId(),
                order.getCustomerId(),
                items,
                order.getTotal(),
                order.getStatus().name(),
                order.getCreatedAt()
        );
    }
}
