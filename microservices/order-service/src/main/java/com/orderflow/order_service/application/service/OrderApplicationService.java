package com.orderflow.order_service.application.service;

import com.orderflow.order_service.application.event.OrderCreatedEvent;
import com.orderflow.order_service.application.port.input.CancelOrderUseCase;
import com.orderflow.order_service.application.port.input.CreateOrderUseCase;
import com.orderflow.order_service.application.port.input.GetOrderUseCase;
import com.orderflow.order_service.application.port.input.UpdateOrderUseCase;
import com.orderflow.order_service.application.port.output.DeleteOrderPort;
import com.orderflow.order_service.application.port.output.FindOrderPort;
import com.orderflow.order_service.application.port.output.PublishOrderCreatedEventPort;
import com.orderflow.order_service.application.port.output.SaveOrderPort;
import com.orderflow.order_service.domain.model.Order;
import com.orderflow.order_service.domain.model.OrderStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class OrderApplicationService  implements CreateOrderUseCase ,
        GetOrderUseCase,
        CancelOrderUseCase,
        UpdateOrderUseCase {

    private final SaveOrderPort saveOrderPort;

    private final FindOrderPort findOrderPort;

    private final DeleteOrderPort deleteOrderPort;

    private final PublishOrderCreatedEventPort publishEventPort;


    public OrderApplicationService(SaveOrderPort saveOrderPort,
                                   FindOrderPort findOrderPort,
                                   DeleteOrderPort deleteOrderPort,
                                   PublishOrderCreatedEventPort publishEventPort) {
        this.saveOrderPort = saveOrderPort;
        this.findOrderPort = findOrderPort;
        this.deleteOrderPort = deleteOrderPort;
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

    @Override
    public Order cancelOrder(UUID orderId) {
        Order order = findOrderPort.findById(orderId)
                .orElseThrow(
                        () -> new RuntimeException(
                                "Order not found"
                        )
                );


        order.cancel();


        return saveOrderPort.save(order);
    }

    @Override
    public Order getOrderById(UUID orderId) {
        return findOrderPort.findById(orderId)
                .orElseThrow(
                        () -> new RuntimeException(
                                "Order not found: " + orderId
                        )
                );
    }

    @Override
    public Order updateStatus(UUID orderId, OrderStatus status) {

        Order order = findOrderPort.findById(orderId)
                .orElseThrow(() ->
                        new RuntimeException("Order not found"));

        switch (status) {

            case INVENTORY_RESERVED ->
                    order.reserveInventory();

            case PAYMENT_COMPLETED ->
                    order.completePayment();

            case PAYMENT_FAILED ->
                    order.failPayment();

            case CANCELLED ->
                    order.cancel();

            default ->
                    throw new IllegalArgumentException(
                            "Invalid order transition");
        }

        return saveOrderPort.save(order);
    }
}
