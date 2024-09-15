package com.example.property.service.payment;

import com.example.property.dto.payment.PaymentRequestDto;
import com.example.property.dto.payment.PaymentResponseDto;
import com.example.property.exception.MethodArgumentNotValidException;

import java.util.List;

public interface PaymentService {
    public void savePayment(PaymentRequestDto paymentRequestDto) throws MethodArgumentNotValidException;
    public void updatePayment(Long id,PaymentRequestDto paymentRequestDto) throws MethodArgumentNotValidException;
    public List<PaymentResponseDto> getAllPayments();
    public PaymentResponseDto getPayment(Long paymentId);
}
