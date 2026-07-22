package com.orderflow.order_service.api.controller;

import com.orderflow.order_service.api.dto.CreateOrderRequest;
import com.orderflow.order_service.api.dto.OrderResponse;
import com.orderflow.order_service.api.dto.UpdateOrderRequest;
import com.orderflow.order_service.api.mapper.OrderApiMapper;
import com.orderflow.order_service.application.port.input.CancelOrderUseCase;
import com.orderflow.order_service.application.port.input.CreateOrderUseCase;
import com.orderflow.order_service.application.port.input.GetOrderUseCase;
import com.orderflow.order_service.application.port.input.UpdateOrderUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;
    private final GetOrderUseCase getOrderUseCase;
    private final UpdateOrderUseCase updateOrderUseCase;
    private final CancelOrderUseCase cancelOrderUseCase;
    private final OrderApiMapper mapper;

    public OrderController(CreateOrderUseCase createOrderUseCase, GetOrderUseCase getOrderUseCase, UpdateOrderUseCase updateOrderUseCase, CancelOrderUseCase cancelOrderUseCase, OrderApiMapper mapper) {
        this.createOrderUseCase = createOrderUseCase;
        this.getOrderUseCase = getOrderUseCase;
        this.updateOrderUseCase = updateOrderUseCase;
        this.cancelOrderUseCase = cancelOrderUseCase;
        this.mapper = mapper;
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
                        mapper.toApplicationItems(request),
                        request.total()
                )
        );
    }
    @GetMapping("/{id}")
    public OrderResponse getOrder(
            @PathVariable UUID id) {

        return OrderResponse.from(
                getOrderUseCase.getOrderById(id)
        );
    }
    @PutMapping("/{id}/status")
    public OrderResponse updateOrder(
            @PathVariable UUID id,

            @Valid
            @RequestBody
            UpdateOrderRequest request) {

        return OrderResponse.from(
                updateOrderUseCase.updateStatus(
                        id,
                        request.status()
                )
        );
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelOrder(
            @PathVariable UUID id) {

        cancelOrderUseCase.cancelOrder(id);
    }
}
