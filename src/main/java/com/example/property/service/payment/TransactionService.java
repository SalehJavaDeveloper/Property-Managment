package com.example.property.service.payment;

import org.springframework.scheduling.annotation.Async;

import java.math.BigDecimal;

public interface TransactionService {

    String getPaymentLink(String transactionId, BigDecimal amount);

    @Async
    void updatePaymentStatus(String transactionId);

}
