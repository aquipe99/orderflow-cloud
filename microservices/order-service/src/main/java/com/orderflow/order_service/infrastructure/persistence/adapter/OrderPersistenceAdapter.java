package com.orderflow.order_service.infrastructure.persistence.adapter;

import com.orderflow.order_service.application.port.output.SaveOrderPort;
import com.orderflow.order_service.domain.model.Order;
import com.orderflow.order_service.infrastructure.persistence.mapper.OrderPersistenceMapper;
import com.orderflow.order_service.infrastructure.persistence.repository.OrderJpaRepository;
import org.springframework.stereotype.Component;

@Component
public class OrderPersistenceAdapter implements SaveOrderPort {

    private final OrderJpaRepository repository;

    private final OrderPersistenceMapper mapper;



    public OrderPersistenceAdapter(
            OrderJpaRepository repository,
            OrderPersistenceMapper mapper) {

        this.repository = repository;
        this.mapper = mapper;

    }

    @Override
    public Order save(Order order) {
        repository.save(
                mapper.toEntity(order)
        );


        return order;
    }
}
