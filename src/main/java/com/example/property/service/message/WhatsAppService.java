package com.example.property.service.message;

import com.example.property.dto.whatsAppDto.*;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WhatsAppService {

    public ResponseEntity<String> sendWhatsAppMessage(WhatsAppBodyRequest request,String apiKey) {
        WhatsAppTextRequest whatsAppTextRequest = new WhatsAppTextRequest();
        WhatsAppBodyRequest whatsAppBodyRequest = new WhatsAppBodyRequest();

        whatsAppTextRequest.setBody(request.getText().getBody());

        whatsAppBodyRequest.setMessaging_product(request.getMessaging_product());
        whatsAppBodyRequest.setRecipient_type(request.getRecipient_type());
        whatsAppBodyRequest.setTo(request.getTo());
        whatsAppBodyRequest.setType(request.getType());
        whatsAppBodyRequest.setText(whatsAppTextRequest);

        String url = "https://waba-sandbox.360dialog.io/v1/messages";
        HttpHeaders headers = new HttpHeaders();
        headers.set("D360-API-KEY",apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<WhatsAppBodyRequest> requestRootHttpEntity = new HttpEntity<>(whatsAppBodyRequest, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestRootHttpEntity, String.class);

        return responseEntity;
    }
}
