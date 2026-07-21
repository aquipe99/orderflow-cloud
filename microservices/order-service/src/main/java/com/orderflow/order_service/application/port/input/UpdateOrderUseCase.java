package com.orderflow.order_service.application.port.input;

import com.orderflow.order_service.domain.model.Order;
import com.orderflow.order_service.domain.model.OrderStatus;

import java.math.BigDecimal;
import java.util.UUID;

public interface UpdateOrderUseCase {
    Order updateStatus(
            UUID orderId,
            OrderStatus status
    );
}
