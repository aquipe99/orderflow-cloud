package com.orderflow.order_service.api.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateOrderRequest(

        @NotNull(message = "customerId is required")
        Long customerId,


        @NotNull(message = "total is required")
        @DecimalMin(
                value = "0.01",
                message = "total must be greater than zero"
        )
        BigDecimal total

) {
}
