package com.orderflow.order_service.application.port.output;

import com.orderflow.order_service.domain.model.Order;

import java.util.Optional;
import java.util.UUID;

public interface FindOrderPort {
    Optional<Order> findById(UUID id);
}
