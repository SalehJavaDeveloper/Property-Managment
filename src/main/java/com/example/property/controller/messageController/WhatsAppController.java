package com.example.property.controller.messageController;

//import com.example.property.dto.whatsAppDto.WhatsAppRequest;
import com.example.property.dto.whatsAppDto.WhatsAppBodyRequest;
import com.example.property.service.message.WhatsAppService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message/whatsapp")
public class WhatsAppController {
    private final WhatsAppService whatsAppService;


    public WhatsAppController(WhatsAppService whatsAppService) {
        this.whatsAppService = whatsAppService;
    }

    @PostMapping("/message")
    public ResponseEntity<String> sendWhatsAppMessage(@RequestBody WhatsAppBodyRequest whatsAppRequest, @RequestHeader String apiKey){
        ResponseEntity<String> stringResponseEntity = whatsAppService.sendWhatsAppMessage(whatsAppRequest,apiKey);
        return new ResponseEntity<>(stringResponseEntity.getBody(), HttpStatus.OK);
    }
}
