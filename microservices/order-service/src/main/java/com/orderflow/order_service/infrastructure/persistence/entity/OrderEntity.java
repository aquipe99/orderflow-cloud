package com.orderflow.order_service.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name="orders")
public class OrderEntity {
    @Id
    private UUID id;


    @Column(name = "customer_id")
    private Long customerId;


    private BigDecimal total;


    private String status;


    @Column(name = "created_at")
    private Instant createdAt;


    protected OrderEntity() {

    }



    public OrderEntity(
            UUID id,
            Long customerId,
            BigDecimal total,
            String status,
            Instant createdAt) {


        this.id = id;
        this.customerId = customerId;
        this.total = total;
        this.status = status;
        this.createdAt = createdAt;

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
