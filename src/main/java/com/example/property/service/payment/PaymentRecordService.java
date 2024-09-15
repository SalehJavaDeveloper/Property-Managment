package com.example.property.service.payment;

import java.util.List;
import java.util.Map;

public interface PaymentRecordService {
    List<Map<String, Object>> getPaymentCounts();
}
