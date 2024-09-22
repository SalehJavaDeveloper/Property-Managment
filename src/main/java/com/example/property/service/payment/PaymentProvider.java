package com.example.property.service.payment;

import com.example.property.dto.payment.PaymentInitializationRequestDto;
import com.example.property.dto.payment.PaymentInitializationResponseDto;
import com.example.property.dto.payment.PaymentStatusResponseDto;

public interface PaymentProvider {
    PaymentInitializationResponseDto initialize(PaymentInitializationRequestDto request);

    PaymentStatusResponseDto getStatus(String paymentId);
}
