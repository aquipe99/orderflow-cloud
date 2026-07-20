package com.orderflow.order_service.application.service;

import com.orderflow.order_service.application.event.OrderCreatedEvent;
import com.orderflow.order_service.application.port.input.CreateOrderUseCase;
import com.orderflow.order_service.application.port.output.PublishOrderCreatedEventPort;
import com.orderflow.order_service.application.port.output.SaveOrderPort;
import com.orderflow.order_service.domain.model.Order;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class OrderApplicationService  implements CreateOrderUseCase {

    private SaveOrderPort saveOrderPort;

    private final PublishOrderCreatedEventPort publishEventPort;

    public OrderApplicationService(SaveOrderPort saveOrderPort, PublishOrderCreatedEventPort publishEventPort) {
        this.saveOrderPort = saveOrderPort;
        this.publishEventPort = publishEventPort;
    }



    @Override
    public Order createOrder(Long customerId, BigDecimal total) {
        Order order = new Order(UUID.randomUUID(),customerId,total);
        Order savedOrder = saveOrderPort.save(order);

        OrderCreatedEvent event= new OrderCreatedEvent(
                savedOrder.getId(),

                savedOrder.getCustomerId(),

                savedOrder.getTotal(),

                savedOrder.getCreatedAt()
        );
        publishEventPort.publish(event);

        return savedOrder;

    }
}
