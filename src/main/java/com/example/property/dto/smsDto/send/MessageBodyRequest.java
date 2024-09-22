package com.example.property.dto.smsDto.send;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MessageBodyRequest {
    private String msisdn;

    @Override
    public String toString() {
        return "MessageBodyRequest{" +
                "msisdn='" + msisdn + '\'' +
                '}';
    }
}
