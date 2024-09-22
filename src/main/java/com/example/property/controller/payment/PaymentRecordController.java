package com.example.property.controller.payment;


import com.example.property.dto.payment.PaymentRecordDto;
import com.example.property.handler.SuccessDetails;
import com.example.property.service.impl.record.PaymentRecordServiceImpl;

import com.example.property.service.payment.PaymentRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Tag(name = "Payment_Record")
public class PaymentRecordController {
    private final PaymentRecordService paymentRecordService;

    @PostMapping("/payment-record")
    @Operation(summary = "Get Payment Records by date")
    ResponseEntity<SuccessDetails<List<Map<String, Object>>>> getPaymentRecord(@RequestBody PaymentRecordDto paymentRecordDto){
        List<Map<String, Object>> paymentRecords = paymentRecordService.getPaymentCounts(paymentRecordDto);
        return ResponseEntity.ok(new SuccessDetails<>(paymentRecords, HttpStatus.OK.value(), true));
    }
}
