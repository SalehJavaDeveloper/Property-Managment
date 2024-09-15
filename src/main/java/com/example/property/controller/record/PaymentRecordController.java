package com.example.property.controller.record;

import com.example.property.service.payment.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
@Tag(name = "PaymentRecord")
public class PaymentRecordController {



    @Operation(summary = "Return payment record")
    @GetMapping("/payment-record")
    public String getTransactionLink(){

        return "";
    }
}