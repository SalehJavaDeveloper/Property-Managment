package com.example.property.controller.payment;


import com.example.property.dto.payment.PaymentRequestDto;
import com.example.property.dto.payment.PaymentResponseDto;
import com.example.property.exception.MethodArgumentNotValidException;
import com.example.property.handler.SuccessDetails;

import com.example.property.service.impl.payment_impl.PaymentServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Tag(name = "Payment")
public class PaymentController {

    private final PaymentServiceImpl paymentService;

    @PostMapping("/payment")
    @Operation(description = "Save operation",
            summary = "This is the summary for save payment data",
            responses = {
                    @ApiResponse(description = "Success",responseCode = "200"),
                    @ApiResponse(description = "Unauthorized: Invalid Token",responseCode = "403")
            })
    public ResponseEntity<SuccessDetails<String>> savePayment(@RequestBody PaymentRequestDto paymentRequestDto) throws MethodArgumentNotValidException {
        paymentService.savePayment(paymentRequestDto);
        return ResponseEntity.ok(new SuccessDetails<>("Payment Save Successfully!", HttpStatus.OK.value(),true));
    }

    @PutMapping("/payment/{id}")
    @Operation(description = "Save operation",
            summary = "This is the summary for update payment data",
            responses = {
                    @ApiResponse(description = "Success",responseCode = "200"),
                    @ApiResponse(description = "Unauthorized: Invalid Token",responseCode = "403")
            })
    public ResponseEntity<SuccessDetails<String>> updatePayment(@PathVariable("id") Long id,@RequestBody  PaymentRequestDto paymentRequestDto) throws MethodArgumentNotValidException {
        paymentService.updatePayment(id,paymentRequestDto);
        return ResponseEntity.ok(new SuccessDetails<>("Payment update Successfully!", HttpStatus.OK.value(),true));
    }

    @GetMapping("/payment")
    @Operation(summary = "Find All payments")
    ResponseEntity<SuccessDetails<List<PaymentResponseDto>>> findAllPayments(){
        return ResponseEntity.ok(new SuccessDetails<>(paymentService.getAllPayments(),HttpStatus.OK.value(),true));
    }

    @GetMapping("/payment/{paymentId}")
    @Operation(summary = "Find Payments by id")
    ResponseEntity<SuccessDetails<PaymentResponseDto>> findPaymentById(@PathVariable("paymentId") Long paymentId) throws MethodArgumentNotValidException {
        return ResponseEntity.ok(new SuccessDetails<>(paymentService.getPayment(paymentId),HttpStatus.OK.value(),true));
    }
}