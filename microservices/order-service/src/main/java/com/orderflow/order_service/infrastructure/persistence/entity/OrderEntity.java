package com.orderflow.order_service.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import java.util.List;

@Entity
@Table(name="orders")
public class OrderEntity {
    @Id
    private UUID id;


    @Column(name = "customer_id")
    private Long customerId;

    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<OrderItemEntity> items;

    private BigDecimal total;


    private String status;


    @Column(name = "created_at")
    private Instant createdAt;


    protected OrderEntity() {

    }



    public OrderEntity(
            UUID id,
            Long customerId,
            List<OrderItemEntity> items,
            BigDecimal total,
            String status,
            Instant createdAt) {


        this.id = id;
        this.customerId = customerId;
        this.items = items;
        this.total = total;
        this.status = status;
        this.createdAt = createdAt;
        items.forEach(item ->
                item.setOrder(this)
        );

    }


    public List<OrderItemEntity> getItems() {
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


    public String getStatus() {
        return status;
    }


    public Instant getCreatedAt() {
        return createdAt;
    }
}
