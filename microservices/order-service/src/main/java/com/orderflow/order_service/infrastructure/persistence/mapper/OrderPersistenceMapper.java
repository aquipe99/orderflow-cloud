package com.orderflow.order_service.infrastructure.persistence.mapper;

import com.orderflow.order_service.domain.model.Order;
import com.orderflow.order_service.domain.model.OrderItem;
import com.orderflow.order_service.domain.model.OrderStatus;
import com.orderflow.order_service.infrastructure.persistence.entity.OrderEntity;
import com.orderflow.order_service.infrastructure.persistence.entity.OrderItemEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderPersistenceMapper {
    public OrderEntity toEntity(Order order){

        List<OrderItemEntity> items =
                order.getItems()
                        .stream()
                        .map(item ->
                                new OrderItemEntity(
                                        item.getProductCode(),
                                        item.getQuantity()
                                )
                        )
                        .toList();
        return new OrderEntity(
                order.getId(),
                order.getCustomerId(),
                items,
                order.getTotal(),
                order.getStatus().name(),
                order.getCreatedAt()
        );

    }
    public Order toDomain(OrderEntity entity) {
        List<OrderItem> items =
                entity.getItems()
                        .stream()
                        .map(item ->
                                new OrderItem(
                                        item.getProductCode(),
                                        item.getQuantity()
                                )
                        )
                        .toList();
        return new Order(
                entity.getId(),
                entity.getCustomerId(),
                items,
                entity.getTotal(),
                OrderStatus.valueOf(entity.getStatus()),
                entity.getCreatedAt()
        );
    }

}
