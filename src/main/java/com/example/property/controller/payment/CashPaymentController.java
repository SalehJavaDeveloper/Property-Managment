package com.example.property.controller.payment;

import com.example.property.dto.payment.CashPaymentRequestDto;
import com.example.property.dto.payment.PaymentRequestDto;
import com.example.property.exception.MethodArgumentNotValidException;
import com.example.property.handler.SuccessDetails;
import com.example.property.service.payment.CashPaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
@Tag(name = "Cash_Payment")
public class CashPaymentController {

    private final CashPaymentService cashPaymentService;

    @PostMapping("/cash_payment")
    @Operation(description = "Save operation",
            summary = "This is the summary for save cash payment data",
            responses = {
                    @ApiResponse(description = "Success",responseCode = "200"),
                    @ApiResponse(description = "Unauthorized: Invalid Token",responseCode = "403")
            })
    public ResponseEntity<SuccessDetails<String>> saveCashPayment(@RequestBody CashPaymentRequestDto cashPaymentRequestDto) throws MethodArgumentNotValidException {
        cashPaymentService.saveCashPayment(cashPaymentRequestDto);
        return ResponseEntity.ok(new SuccessDetails<>("Cash Payment Save Successfully!", HttpStatus.OK.value(),true));
    }

}
