package com.example.property.service.impl.payment_impl;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class YigimPaymentInitializationResponse {
    private int code;
    private String url;
    private String message;
}
