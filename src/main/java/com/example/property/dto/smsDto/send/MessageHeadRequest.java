package com.example.property.dto.smsDto.send;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageHeadRequest {

    private String bulkmessage;
    private String scheduled;




}

