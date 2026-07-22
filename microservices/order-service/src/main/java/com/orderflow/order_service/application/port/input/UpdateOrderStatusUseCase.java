package com.orderflow.order_service.application.port.input;

import java.util.UUID;

public interface UpdateOrderStatusUseCase {
    void updateStatus(
            UUID orderId,
            String status
    );
}
