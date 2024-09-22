package com.example.property.service.impl.payment_impl;

import com.example.property.dto.payment.PaymentInitializationRequestDto;
import com.example.property.dto.payment.PaymentInitializationResponseDto;
import com.example.property.dto.payment.PaymentStatusResponseDto;
import com.example.property.feign.YigimFeignClient;
import com.example.property.mapper.payment.PaymentProviderMapper;
import com.example.property.service.payment.PaymentProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@RequiredArgsConstructor
@Service
public class YigimService implements PaymentProvider {

    @Value("${paymentProvider.yigim.merchantId}")
    private String MERCHANT_ID;

    @Value("${paymentProvider.yigim.apiKey}")
    private String API_KEY;

    @Value("${paymentProvider.yigim.biller}")
    private String BILLER;

    @Value("${paymentProvider.yigim.template}")
    private String TEMPLATE;

    @Value("${paymentProvider.yigim.callbackUrl}")
    private String callbackUrl;

    private String RESPONSE_TYPE = "JSON";

    private final YigimFeignClient yigimFeignClient;

    private final PaymentProviderMapper paymentProviderMapper;

    @Override
    public PaymentInitializationResponseDto initialize(PaymentInitializationRequestDto request) {

        YigimPaymentInitializationResponse yigimPaymentInitializationResponse = yigimFeignClient.initialize(
                MERCHANT_ID,
                API_KEY,
                RESPONSE_TYPE,
                request.getId(),
                formatAmount(request.getAmount()),
                request.getCurrency().getCode(),
                BILLER,
                TEMPLATE,
                request.getLanguage().getName(),
                callbackUrl
        );

        PaymentInitializationResponseDto response = paymentProviderMapper
                .toPaymentInitializationResponse(yigimPaymentInitializationResponse);

        return response;
    }

    @Override
    public PaymentStatusResponseDto getStatus(String paymentId) {
        YigimPaymentStatusResponse yigimPaymentStatusResponse = yigimFeignClient.getStatus(
                MERCHANT_ID,
                API_KEY,
                RESPONSE_TYPE,
                paymentId
        );

        PaymentStatusResponseDto response = paymentProviderMapper.toPaymentStatusResponse(yigimPaymentStatusResponse);

        return response;
    }

    // Payment amount in coins, i.e. 50.25 = 5025
    private String formatAmount(BigDecimal amount) {
        return amount
                .setScale(2, BigDecimal.ROUND_HALF_UP)
                .multiply(BigDecimal.valueOf(100))
                .setScale(0, BigDecimal.ROUND_UNNECESSARY)
                .toString();
    }
}
