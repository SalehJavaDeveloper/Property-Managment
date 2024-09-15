package com.example.property.dto.smsDto.report;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageReportRequest {
    private MessageReportHeadRequest messageReportHeadRequest;

    @JsonProperty("operation")
    private String operation;

    @JsonProperty("login")
    private  String  login;

    @JsonProperty("password")
    private String password;

}
