package com.orderflow.order_service.application.port.output;

import java.util.UUID;

public interface DeleteOrderPort {
    void delete(UUID id);
}
