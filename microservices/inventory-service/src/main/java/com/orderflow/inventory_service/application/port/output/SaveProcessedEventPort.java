package com.orderflow.inventory_service.application.port.output;

import java.util.UUID;

public interface SaveProcessedEventPort {
    void save(UUID eventId, String consumer);
}
