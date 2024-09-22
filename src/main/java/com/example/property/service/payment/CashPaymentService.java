package com.example.property.service.payment;

import com.example.property.dto.payment.CashPaymentRequestDto;

public interface CashPaymentService {
    void saveCashPayment(CashPaymentRequestDto cashPaymentRequestDto);
}
