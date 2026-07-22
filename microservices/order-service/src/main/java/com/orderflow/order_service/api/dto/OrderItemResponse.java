package com.orderflow.order_service.api.dto;

import com.orderflow.order_service.domain.model.OrderItem;

public record OrderItemResponse(
        String productCode,

        Integer quantity
) {
    public static OrderItemResponse from(OrderItem item){

        return new OrderItemResponse(
                item.getProductCode(),
                item.getQuantity()
        );
    }
}
