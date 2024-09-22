package com.example.property.mapper.payment;

import com.example.property.dto.payment.PaymentInitializationResponseDto;
import com.example.property.dto.payment.PaymentStatusResponseDto;
import com.example.property.enumuration.PaymentProviderStatus;
import com.example.property.service.impl.payment_impl.YigimPaymentInitializationResponse;
import com.example.property.service.impl.payment_impl.YigimPaymentStatusResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentProviderMapper {
    PaymentInitializationResponseDto toPaymentInitializationResponse(YigimPaymentInitializationResponse yigimResponse);

    @Mapping(target = "status", expression = "java(mapStatus(yigimResponse.getStatus()))")
    PaymentStatusResponseDto toPaymentStatusResponse(YigimPaymentStatusResponse yigimResponse);

    default PaymentProviderStatus mapStatus(String status) {
        return "00".equals(status) ? PaymentProviderStatus.APPROVED : PaymentProviderStatus.DECLINED;
    }
}
