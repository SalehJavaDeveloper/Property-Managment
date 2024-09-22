package com.example.property.enumuration;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum Response {
    PAYMENT_NOT_FOUND("Payment not found", HttpStatus.NOT_FOUND),
    UNIT_NOT_FOUND("Unit not found", HttpStatus.NOT_FOUND),
    INVALID_PAYMENT_GENERATION_TYPE("Invalid payment generation type", HttpStatus.NOT_FOUND),

    UNIT_PROPERTY_MISMATCH("Unit property mismatch", HttpStatus.BAD_REQUEST);

    private String message;
    private HttpStatus status;

    Response(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}
