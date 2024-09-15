package com.example.property.dto.smsDto.send;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MessageRequest {

    @JsonProperty("operation")
    private String operation;


    @JsonProperty("login")
    private  String  login;

    @JsonProperty("password")
    private  String password;

    @JsonProperty("title")
    private String title;

    @JsonProperty("controlid")
    private String controlid;

    @JsonProperty("isbulk")
    private String isbulk;

    private MessageHeadRequest head;
    private List<MessageBodyRequest> body;
}
