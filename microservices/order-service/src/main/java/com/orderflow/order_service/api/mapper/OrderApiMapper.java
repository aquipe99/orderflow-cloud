package com.orderflow.order_service.api.mapper;

import com.orderflow.order_service.api.dto.CreateOrderRequest;
import com.orderflow.order_service.application.dto.request.OrderItemRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderApiMapper {
    public List<OrderItemRequest> toApplicationItems(
            CreateOrderRequest request) {

        return request.items()
                .stream()
                .map(item ->
                        new OrderItemRequest(
                                item.productCode(),
                                item.quantity()
                        )
                )
                .toList();
    }
}
