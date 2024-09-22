package com.example.property.service.payment;

import com.example.property.dto.filter.RequestDto;
import com.example.property.dto.payment.PaymentDetailedResponseDto;
import com.example.property.dto.payment.PaymentGeneralResponseDto;
import com.example.property.dto.payment.PaymentRequestDto;
import com.example.property.entity.payment.PaymentGeneralView;
import com.example.property.exception.MethodArgumentNotValidException;
import org.springframework.data.domain.Page;

public interface PaymentService {
    public void savePayment(PaymentRequestDto paymentRequestDto) throws MethodArgumentNotValidException;
    public void updatePayment(Long id,PaymentRequestDto paymentRequestDto) throws MethodArgumentNotValidException;

    PaymentGeneralResponseDto getPaymentList(int pageNum, int pageSize);
    PaymentDetailedResponseDto getPaymentDetails(Long id);
    Page<PaymentGeneralResponseDto.PaymentDto> getSpecification(RequestDto requestDto);

}
