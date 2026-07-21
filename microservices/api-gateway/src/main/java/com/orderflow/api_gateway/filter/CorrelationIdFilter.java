package com.orderflow.api_gateway.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;
import java.util.UUID;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 1)
public class CorrelationIdFilter extends OncePerRequestFilter {

    private static final Logger log =
            LoggerFactory.getLogger(CorrelationIdFilter.class);
    public static final String CORRELATION_ID_HEADER = "X-Correlation-Id";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String correlationId = request.getHeader(CORRELATION_ID_HEADER);

        if (correlationId == null || correlationId.isBlank()) {
            correlationId = UUID.randomUUID().toString();
        }

        MDC.put(CORRELATION_ID_HEADER, correlationId);

        response.setHeader(CORRELATION_ID_HEADER, correlationId);
        log.info(
                "Incoming request {} {} correlationId={}",
                request.getMethod(),
                request.getRequestURI(),
                correlationId
        );
        try {

            filterChain.doFilter(request, response);

        } finally {
            log.info(
                    "Completed request {} {} status={} correlationId={}",
                    request.getMethod(),
                    request.getRequestURI(),
                    response.getStatus(),
                    correlationId
            );
            MDC.remove(CORRELATION_ID_HEADER);

        }
    }
}
