package com.orderflow.order_service.application.port.input;

import com.orderflow.order_service.application.dto.request.OrderItemRequest;
import com.orderflow.order_service.domain.model.Order;

import java.math.BigDecimal;
import java.util.List;

public interface CreateOrderUseCase {
    Order createOrder(
            Long customerId,
            List<OrderItemRequest> items,
            BigDecimal total
    );
}
