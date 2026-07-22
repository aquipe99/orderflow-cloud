package com.orderflow.inventory_service.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "inventory_items")
public class InventoryItemEntity {
    @Id
    private UUID id;


    @Column(
            name = "product_code",
            nullable = false,
            unique = true
    )
    private String productCode;


    @Column(nullable = false)
    private String description;


    @Column(
            name = "available_stock",
            nullable = false
    )
    private Integer availableStock;


    @Column(
            name = "reserved_stock",
            nullable = false
    )
    private Integer reservedStock;


    @Column(
            name = "created_at",
            nullable = false
    )
    private Instant createdAt;



    protected InventoryItemEntity(){

    }



    public InventoryItemEntity(
            UUID id,
            String productCode,
            String description,
            Integer availableStock,
            Integer reservedStock,
            Instant createdAt) {

        this.id = id;
        this.productCode = productCode;
        this.description = description;
        this.availableStock = availableStock;
        this.reservedStock = reservedStock;
        this.createdAt = createdAt;
    }



    public UUID getId() {
        return id;
    }


    public String getProductCode() {
        return productCode;
    }


    public String getDescription() {
        return description;
    }


    public Integer getAvailableStock() {
        return availableStock;
    }


    public Integer getReservedStock() {
        return reservedStock;
    }


    public Instant getCreatedAt() {
        return createdAt;
    }
}
