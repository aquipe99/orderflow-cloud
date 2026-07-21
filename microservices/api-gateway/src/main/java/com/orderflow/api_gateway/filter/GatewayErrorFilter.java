package com.orderflow.api_gateway.filter;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.HandlerFilterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import java.time.Instant;
import java.util.Map;

@Component
public class GatewayErrorFilter {
    public HandlerFilterFunction<ServerResponse, ServerResponse> handleErrors() {


        return (request, next) -> {

            try {

                return next.handle(request);


            } catch (Exception ex) {


                return ServerResponse
                        .status(HttpStatus.SERVICE_UNAVAILABLE)
                        .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                        .body(
                                Map.of(
                                        "timestamp", Instant.now(),
                                        "status", 503,
                                        "error", "Service Unavailable",
                                        "message", ex.getMessage(),
                                        "path", request.path()
                                )
                        );

            }

        };

    }
}
