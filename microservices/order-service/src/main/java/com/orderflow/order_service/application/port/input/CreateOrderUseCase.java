package com.orderflow.order_service.application.port.input;

import com.orderflow.order_service.domain.model.Order;

import java.math.BigDecimal;

public interface CreateOrderUseCase {
    Order createOrder(
            Long customerId,
            BigDecimal total
    );
}
