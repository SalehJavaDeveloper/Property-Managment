package com.example.property.dto.whatsAppDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WhatsAppBodyRequest {
    private String messaging_product;
    private String recipient_type;
    private String to;
    private String type;
    private WhatsAppTextRequest text;
}
