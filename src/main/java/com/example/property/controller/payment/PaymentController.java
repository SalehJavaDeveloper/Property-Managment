package com.example.property.controller.payment;


import com.example.property.dto.filter.RequestDto;
import com.example.property.dto.payment.PaymentDetailedResponseDto;
import com.example.property.dto.payment.PaymentGeneralResponseDto;
import com.example.property.dto.payment.PaymentRequestDto;
import com.example.property.exception.MethodArgumentNotValidException;
import com.example.property.handler.SuccessDetails;
import com.example.property.service.impl.payment_impl.PaymentServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Tag(name = "Payment")
public class PaymentController {

    private final PaymentServiceImpl paymentService;

    @PostMapping("/payments")
    @Operation(description = "Save operation",
            summary = "This is the summary for save payment data",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "Unauthorized: Invalid Token", responseCode = "403")
            })
    public ResponseEntity<SuccessDetails<String>> savePayment(@RequestBody PaymentRequestDto paymentRequestDto) throws MethodArgumentNotValidException {
        paymentService.savePayment(paymentRequestDto);
        return ResponseEntity.ok(new SuccessDetails<>("Payment Save Successfully!", HttpStatus.OK.value(), true));
    }

    @GetMapping("/payments")
    @Operation(summary = "Find all payments")
    ResponseEntity<SuccessDetails<PaymentGeneralResponseDto>> getPaymentList(@RequestParam int pageNum,
                                                                             @RequestParam int pageSize) {
        var response = paymentService.getPaymentList(pageNum, pageSize);
        return ResponseEntity.ok(new SuccessDetails<>(response, HttpStatus.OK.value(), true));
    }

    @GetMapping("/payments/{id}")
    @Operation(summary = "Get payment details")
    ResponseEntity<SuccessDetails<PaymentDetailedResponseDto>> getPaymentDetails(@PathVariable("id") Long paymentId) {
        var response = paymentService.getPaymentDetails(paymentId);
        return ResponseEntity.ok(new SuccessDetails<>(response, HttpStatus.OK.value(), true));
    }

    @PutMapping("/payments/{id}")
    @Operation(description = "Save operation",
            summary = "This is the summary for update payment data",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "Unauthorized: Invalid Token", responseCode = "403")
            })
    public ResponseEntity<SuccessDetails<String>> updatePayment(@PathVariable("id") Long id, @RequestBody PaymentRequestDto paymentRequestDto) throws MethodArgumentNotValidException {
        paymentService.updatePayment(id, paymentRequestDto);
        return ResponseEntity.ok(new SuccessDetails<>("Payment update Successfully!", HttpStatus.OK.value(), true));
    }


    @PostMapping("/specification/payments")
    @Operation(description = "Specification and filtering into payment.")
    ResponseEntity<SuccessDetails<Page<PaymentGeneralResponseDto.PaymentDto>>> getPaymentPage(@RequestBody RequestDto requestDto) {
        var response = paymentService.getSpecification(requestDto);
        return ResponseEntity.ok(new SuccessDetails<>(response, HttpStatus.OK.value(), true));
    }
}