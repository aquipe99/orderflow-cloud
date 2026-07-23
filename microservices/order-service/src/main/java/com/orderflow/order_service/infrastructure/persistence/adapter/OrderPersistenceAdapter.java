package com.orderflow.order_service.infrastructure.persistence.adapter;

import com.orderflow.order_service.application.port.output.DeleteOrderPort;
import com.orderflow.order_service.application.port.output.FindOrderPort;
import com.orderflow.order_service.application.port.output.SaveOrderPort;
import com.orderflow.order_service.domain.model.Order;
import com.orderflow.order_service.infrastructure.persistence.entity.OrderEntity;
import com.orderflow.order_service.infrastructure.persistence.mapper.OrderPersistenceMapper;
import com.orderflow.order_service.infrastructure.persistence.repository.OrderJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class OrderPersistenceAdapter implements SaveOrderPort,
        FindOrderPort,
        DeleteOrderPort {

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

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Order> findById(UUID id) {
        System.out.println("A");

        Optional<OrderEntity> entity = repository.findById(id);

        System.out.println("B");

        return entity.map(e -> {

            System.out.println("C");

            return mapper.toDomain(e);
        });
//        return repository.findById(id)
//                .map(mapper::toDomain);
    }
}
