package com.orderflow.order_service.domain.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class Order {
    private final UUID id;


    private final Long customerId;

    private final List<OrderItem> items;
    private final BigDecimal total;


    private OrderStatus status;


    private final Instant createdAt;



    public Order(
            UUID id,
            Long customerId,
            List<OrderItem> items,
            BigDecimal total) {
        validateTotal(total);
        if(items == null || items.isEmpty()){

            throw new IllegalArgumentException(
                    "Order must have items"
            );
        }
        this.id = id;
        this.customerId = customerId;
        this.items = items;
        this.total = total;
        this.status = OrderStatus.CREATED;
        this.createdAt = Instant.now();

    }
    public Order(
            UUID id,
            Long customerId,
            List<OrderItem> items,
            BigDecimal total,
            OrderStatus status,
            Instant createdAt) {

        validateTotal(total);

        this.id = id;
        this.customerId = customerId;
        this.items = items;
        this.total = total;
        this.status = status;
        this.createdAt = createdAt;
    }


    private void validateTotal(BigDecimal total) {


        if(total == null ||
                total.compareTo(BigDecimal.ZERO) <= 0) {


            throw new IllegalArgumentException(
                    "Order total must be greater than zero"
            );

        }

    }

    public void reserveInventory(){

        if(this.status != OrderStatus.CREATED){

            throw new IllegalStateException(
                    "Order cannot reserve inventory"
            );
        }


        this.status = OrderStatus.INVENTORY_RESERVED;

    }
    public void completePayment(){

        if(this.status != OrderStatus.INVENTORY_RESERVED){

            throw new IllegalStateException(
                    "Payment cannot be completed"
            );
        }


        this.status = OrderStatus.PAYMENT_COMPLETED;

    }

    public void failPayment(){

        this.status = OrderStatus.PAYMENT_FAILED;

    }
    public void cancel(){

        this.status = OrderStatus.CANCELLED;

    }
    public List<OrderItem> getItems() {
        return items;
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
