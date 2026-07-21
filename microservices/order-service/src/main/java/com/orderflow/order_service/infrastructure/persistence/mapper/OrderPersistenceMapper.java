package com.orderflow.order_service.infrastructure.persistence.mapper;

import com.orderflow.order_service.domain.model.Order;
import com.orderflow.order_service.domain.model.OrderStatus;
import com.orderflow.order_service.infrastructure.persistence.entity.OrderEntity;
import org.springframework.stereotype.Component;

@Component
public class OrderPersistenceMapper {
    public OrderEntity toEntity(Order order){
        return new OrderEntity(
                order.getId(),
                order.getCustomerId(),
                order.getTotal(),
                order.getStatus().name(),
                order.getCreatedAt()
        );

    }
    public Order toDomain(OrderEntity entity) {

        return new Order(
                entity.getId(),
                entity.getCustomerId(),
                entity.getTotal(),
                OrderStatus.valueOf(entity.getStatus()),
                entity.getCreatedAt()
        );
    }

}
