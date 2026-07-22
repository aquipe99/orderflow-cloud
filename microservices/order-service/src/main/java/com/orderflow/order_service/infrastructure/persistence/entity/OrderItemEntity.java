package com.orderflow.order_service.infrastructure.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_code", nullable = false)
    private String productCode;

    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;

    protected OrderItemEntity() {
    }

    public OrderItemEntity(
            String productCode,
            Integer quantity) {

        this.productCode = productCode;
        this.quantity = quantity;
    }
    public void setOrder(OrderEntity order) {
        this.order = order;
    }
    public Long getId() {
        return id;
    }

    public String getProductCode() {
        return productCode;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
