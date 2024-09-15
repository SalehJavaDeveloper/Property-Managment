package com.example.property.service.payment;

import com.example.property.dto.payment.TransactionDto;

public interface TransactionService {

    String makePayment(long paymentId, long userId);
}
