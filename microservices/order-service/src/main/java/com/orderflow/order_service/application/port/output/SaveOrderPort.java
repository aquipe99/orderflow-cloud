package com.orderflow.order_service.application.port.output;

import com.orderflow.order_service.domain.model.Order;

public interface SaveOrderPort {
    Order save(Order order);
}
