package com.orderflow.order_service.domain.model;

public class OrderItem {
    private final String productCode;

    private final Integer quantity;


    public OrderItem(
            String productCode,
            Integer quantity) {

        if(productCode == null || productCode.isBlank()){
            throw new IllegalArgumentException(
                    "Product code is required"
            );
        }


        if(quantity == null || quantity <= 0){
            throw new IllegalArgumentException(
                    "Quantity must be greater than zero"
            );
        }


        this.productCode = productCode;
        this.quantity = quantity;
    }


    public String getProductCode() {
        return productCode;
    }


    public Integer getQuantity() {
        return quantity;
    }
}
