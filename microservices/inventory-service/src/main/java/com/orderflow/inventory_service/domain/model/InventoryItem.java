package com.orderflow.inventory_service.domain.model;

import java.time.Instant;
import java.util.UUID;

public class InventoryItem {

    private final UUID id;
    private final String productCode;
    private final String description;
    private Integer availableStock;
    private Integer reservedStock;
    private final Instant createdAt;

    public InventoryItem(
            UUID id,
            String productCode,
            String description,
            Integer availableStock,
            Integer reservedStock,
            Instant createdAt) {
        if(productCode == null || productCode.isBlank()){
            throw new IllegalArgumentException(
                    "Product code is required"
            );
        }
        if(availableStock == null || availableStock < 0){

            throw new IllegalArgumentException(
                    "Stock cannot be negative"
            );
        }

        if(reservedStock == null || reservedStock < 0){

            throw new IllegalArgumentException(
                    "Reserved stock cannot be negative"
            );
        }
        this.id = id;
        this.productCode = productCode;
        this.description = description;
        this.availableStock = availableStock;
        this.reservedStock = reservedStock;
        this.createdAt = createdAt;
    }
    public void reserveStock(Integer quantity){
        if(quantity <= 0){
            throw new IllegalArgumentException(
                    "Quantity must be greater than zero"
            );
        }
        if(availableStock < quantity){

            throw new IllegalStateException(
                    "Insufficient stock"
            );
        }
        availableStock -= quantity;
        reservedStock += quantity;
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
