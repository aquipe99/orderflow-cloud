package com.orderflow.order_service.application.port.output;

import com.orderflow.order_service.application.event.OrderCreatedEvent;

public interface PublishOrderCreatedEventPort {
    void publish(OrderCreatedEvent event);
}
