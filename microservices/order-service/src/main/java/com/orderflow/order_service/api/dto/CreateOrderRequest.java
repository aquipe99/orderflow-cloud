package com.orderflow.order_service.api.dto;

import com.orderflow.order_service.application.dto.request.OrderItemRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record CreateOrderRequest(

        @NotNull(message = "customerId is required")
        Long customerId,

        @NotEmpty(message = "items are required")
        List<@Valid OrderItemRequest> items,

        @NotNull(message = "total is required")
        @DecimalMin(
                value = "0.01",
                message = "total must be greater than zero"
        )
        BigDecimal total

) {
}
