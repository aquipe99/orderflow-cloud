package com.orderflow.order_service.application.port.input;

import com.orderflow.order_service.domain.model.Order;

import java.util.UUID;

public interface GetOrderUseCase {
    Order getOrderById(UUID orderId);
}
