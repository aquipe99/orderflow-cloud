package com.orderflow.order_service.api.dto;

import com.orderflow.order_service.domain.model.Order;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record OrderResponse(
        UUID id,
        Long customerId,
        BigDecimal total,
        String status,
        Instant createdAt
) {
    public static OrderResponse from(Order order) {


        return new OrderResponse(
                order.getId(),
                order.getCustomerId(),
                order.getTotal(),
                order.getStatus().name(),
                order.getCreatedAt()
        );
    }
}
