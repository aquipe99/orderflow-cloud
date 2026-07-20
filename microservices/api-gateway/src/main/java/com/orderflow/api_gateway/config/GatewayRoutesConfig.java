package com.orderflow.api_gateway.config;

import com.orderflow.api_gateway.filter.CorrelationIdFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;
import org.springframework.web.servlet.function.RequestPredicates;

import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.uri;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;


@Configuration
public class GatewayRoutesConfig {

    private final CorrelationIdFilter  correlationIdFilter;


    public GatewayRoutesConfig(CorrelationIdFilter correlationIdFilter) {
        this.correlationIdFilter = correlationIdFilter;
    }

    @Bean
    public RouterFunction<ServerResponse> orderRoute() {


        return route("order-service")

                .route(
                        RequestPredicates
                                .path("/api/orders/**"),
                        http()
                )
                .before(uri("http://localhost:8081"))
                .filter(
                        correlationIdFilter
                                .addCorrelationId()
                )

                .build();
    }
}
