package com.example.property.controller.payment;

import com.example.property.service.payment.PaymentRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller("PaymentRecordController")
@RequiredArgsConstructor
@Tag(name = "Payment_Record")

public class PaymentRecordController {
    private final PaymentRecordService paymentRecordService;

    @RequestMapping("/payment-record")
    @Operation(summary = "Get Payment Records by date")
    public List<Map<String, Object>> getPaymentRecord(){
        return paymentRecordService.getPaymentCounts();
    }
}