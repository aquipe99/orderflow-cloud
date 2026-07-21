package com.orderflow.order_service.api.dto;

import com.orderflow.order_service.domain.model.OrderStatus;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record UpdateOrderRequest(

        @NotNull
        OrderStatus status

) {
}
