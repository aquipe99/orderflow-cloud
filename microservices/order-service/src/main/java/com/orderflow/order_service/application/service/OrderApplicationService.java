package com.orderflow.order_service.application.service;

import com.orderflow.order_service.application.port.input.CreateOrderUseCase;
import com.orderflow.order_service.application.port.output.SaveOrderPort;
import com.orderflow.order_service.domain.model.Order;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class OrderApplicationService  implements CreateOrderUseCase {

    private SaveOrderPort saveOrderPort;

    public OrderApplicationService(SaveOrderPort saveOrderPort) {
        this.saveOrderPort = saveOrderPort;
    }



    @Override
    public Order createOrder(Long customerId, BigDecimal total) {
        Order order = new Order(UUID.randomUUID(),customerId,total);
        return  saveOrderPort.save(order);
    }
}
