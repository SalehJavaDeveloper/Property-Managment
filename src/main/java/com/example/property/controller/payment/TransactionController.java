//package com.example.property.controller.payment;
//
//import com.example.property.dto.payment.TransactionDto;
//import com.example.property.service.payment.TransactionService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//@Controller
//@RequiredArgsConstructor
//@Tag(name = "Transaction")
//public class TransactionController {
//
//    private final TransactionService transactionService;
//
//    @Operation(summary = "Get transaction link")
//    @GetMapping("/transaction")
//    public String getTransactionLink(@PathVariable long paymentId, long userId){
//
//        String link = transactionService.makePayment(paymentId, userId);
//        return link;
//    }
//}
