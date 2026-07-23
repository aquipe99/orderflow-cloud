package com.orderflow.inventory_service.application.port.output;

import java.util.UUID;

public interface ExistsProcessedEventPort {
    boolean exists(UUID eventId);
}
