package com.example.property.service.impl.payment_impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class YigimPaymentStatusResponse {
    private String reference;

    private LocalDateTime dateTime;

    private String method;

    private String type;

    private String token;

    private String pan;

    private String expiry;

    private String amount;

    private String fee;

    private String currency;

    private String biller;

    private String system;

    private String issuer;

    private String rrn;

    private String approval;

    @JsonProperty("3ds")
    private String threeDs;

    private String status;

    private int code;

    private String message;

    //todo add refund/extra fields

}
