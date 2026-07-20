package com.orderflow.order_service.domain.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class Order {
    private final UUID id;


    private final Long customerId;


    private final BigDecimal total;


    private OrderStatus status;


    private final Instant createdAt;



    public Order(
            UUID id,
            Long customerId,
            BigDecimal total) {


        validateTotal(total);


        this.id = id;

        this.customerId = customerId;

        this.total = total;

        this.status = OrderStatus.CREATED;

        this.createdAt = Instant.now();

    }



    private void validateTotal(BigDecimal total) {


        if(total == null ||
                total.compareTo(BigDecimal.ZERO) <= 0) {


            throw new IllegalArgumentException(
                    "Order total must be greater than zero"
            );

        }

    }



    public UUID getId() {
        return id;
    }


    public Long getCustomerId() {
        return customerId;
    }


    public BigDecimal getTotal() {
        return total;
    }


    public OrderStatus getStatus() {
        return status;
    }


    public Instant getCreatedAt() {
        return createdAt;
    }
}
