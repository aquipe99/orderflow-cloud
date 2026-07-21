package com.orderflow.order_service.api.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFound(
            ResourceNotFoundException exception,
            HttpServletRequest request
    ){


        ApiError error = new ApiError(

                Instant.now(),

                HttpStatus.NOT_FOUND.value(),

                "RESOURCE_NOT_FOUND",

                exception.getMessage(),

                request.getRequestURI()

        );


        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);

    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGenericException(
            Exception exception,
            HttpServletRequest request
    ){


        ApiError error = new ApiError(

                Instant.now(),

                HttpStatus.INTERNAL_SERVER_ERROR.value(),

                "INTERNAL_ERROR",

                exception.getMessage(),

                request.getRequestURI()

        );


        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);

    }
}
