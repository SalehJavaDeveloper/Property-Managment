package com.example.property.controller.payment;

import com.example.property.service.payment.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Tag(name = "Payment webhook")
public class PaymentProviderCallbackController {

    private final TransactionService transactionService;

    @Operation(summary = "Update payment transaction status")
    @GetMapping("/callback/payment/yigim")
    public ResponseEntity<?> update(@RequestParam String reference) {
        transactionService.updatePaymentStatus(reference);
        return ResponseEntity.ok().build();
    }
}
