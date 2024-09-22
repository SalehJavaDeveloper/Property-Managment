package com.example.property.feign;


import com.example.property.service.impl.payment_impl.YigimPaymentInitializationResponse;
import com.example.property.service.impl.payment_impl.YigimPaymentStatusResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "YigimFeignClient", url = "${paymentProvider.yigim.url}")
public interface YigimFeignClient {
    String MERCHANT_ID_HEADER = "X-Merchant";
    String API_KEY_HEADER = "X-API-Key";
    String RESPONSE_TYPE_HEADER = "X-TYPE";

    @GetMapping("/payment/create")
    YigimPaymentInitializationResponse initialize(
            @RequestHeader(name = MERCHANT_ID_HEADER) String merchantId,
            @RequestHeader(name = API_KEY_HEADER) String apiKey,
            @RequestHeader(name = RESPONSE_TYPE_HEADER) String responseType,
            @RequestParam String reference,
            @RequestParam String amount,
            @RequestParam int currency,
            @RequestParam String biller,
            @RequestParam String template,
            @RequestParam String language,
            @RequestParam String callback
    );

    @GetMapping("/payment/status")
    YigimPaymentStatusResponse getStatus(
            @RequestHeader(name = MERCHANT_ID_HEADER) String merchantId,
            @RequestHeader(name = API_KEY_HEADER) String apiKey,
            @RequestHeader(name = RESPONSE_TYPE_HEADER) String responseType,
            @RequestParam String reference
    );
}
