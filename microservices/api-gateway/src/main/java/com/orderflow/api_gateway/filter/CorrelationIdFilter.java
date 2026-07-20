package com.orderflow.api_gateway.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.HandlerFilterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.UUID;


@Configuration
public class CorrelationIdFilter {

    public HandlerFilterFunction<ServerResponse,ServerResponse> addCorrelationId(){

        return (request, next) -> {


            String correlationId =
                    request.headers()
                            .firstHeader("X-Correlation-ID");


            if (correlationId == null ||
                    correlationId.isBlank()) {

                correlationId =
                        UUID.randomUUID().toString();
            }


            ServerResponse response =
                    next.handle(request);


            return ServerResponse
                    .from(response)
                    .header(
                            "X-Correlation-ID",
                            correlationId
                    )
                    .build();
        };
    }
}
