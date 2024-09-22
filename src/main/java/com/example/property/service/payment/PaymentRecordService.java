package com.example.property.service.payment;

import com.example.property.dto.payment.PaymentRecordDto;

import java.util.List;
import java.util.Map;

public interface PaymentRecordService {
    List<Map<String, Object>> getPaymentCounts(PaymentRecordDto paymentRecordDto);
}
