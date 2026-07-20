package com.orderflow.order_service.api.controller;

import com.orderflow.order_service.api.dto.CreateOrderRequest;
import com.orderflow.order_service.api.dto.OrderResponse;
import com.orderflow.order_service.application.port.input.CreateOrderUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;

    public OrderController(CreateOrderUseCase createOrderUseCase) {
        this.createOrderUseCase = createOrderUseCase;
    }



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse createOrder(
            @RequestHeader(value = "X-Correlation-ID", required = false) String correlationId,
            @Valid @RequestBody CreateOrderRequest request) {

        System.out.println("Correlation ID = " + correlationId);

        return OrderResponse.from(
                createOrderUseCase.createOrder(
                        request.customerId(),
                        request.total()
                )
        );
    }
}
