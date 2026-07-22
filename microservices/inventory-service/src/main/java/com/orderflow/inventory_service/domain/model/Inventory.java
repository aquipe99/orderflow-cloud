package com.orderflow.inventory_service.domain.model;

public class Inventory {
    private final Long id;

    private final String productCode;

    private Integer quantity;

    public Inventory(
            Long id,
            String productCode,
            Integer quantity) {

        validateQuantity(quantity);

        this.id = id;
        this.productCode = productCode;
        this.quantity = quantity;
    }

    private void validateQuantity(Integer quantity) {

        if (quantity == null || quantity < 0) {
            throw new IllegalArgumentException(
                    "Inventory quantity cannot be negative"
            );
        }
    }

    public void reserve(Integer requestedQuantity) {

        if (requestedQuantity <= 0) {
            throw new IllegalArgumentException(
                    "Requested quantity must be greater than zero"
            );
        }

        if (quantity < requestedQuantity) {
            throw new IllegalStateException(
                    "Insufficient inventory"
            );
        }

        quantity -= requestedQuantity;
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
