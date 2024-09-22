package com.example.property.controller.messageController;

import com.example.property.dto.smsDto.report.MessageReportHeadRequest;
import com.example.property.dto.smsDto.send.MessageHeadRequest;
//import com.example.property.service.messageService.SMSService;
import com.example.property.service.message.SMSService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/message/sms")
public class SMSController {

    private final SMSService smsService;

    public SMSController(SMSService smsService) {
        this.smsService = smsService;
    }


    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody MessageHeadRequest messageHeadRequest) {
        ResponseEntity<String> stringResponseEntity = smsService.sendBulkMessages(messageHeadRequest);
//        List<String> responseBody = new ArrayList<>();
//        responseBody.addAll(stringResponseEntity.getBody());
        return new ResponseEntity<>(stringResponseEntity.getBody(), HttpStatus.OK);
    }

    @PostMapping("/report-number")
    public ResponseEntity<String> getReport(@RequestBody MessageReportHeadRequest messageReportHeadRequest){
        ResponseEntity<String> stringResponseEntity = smsService.checkReport(messageReportHeadRequest);
        return new ResponseEntity<>("Report get successfully for number " + stringResponseEntity.getBody(), HttpStatus.OK);
    }

    @PostMapping("/detailed-report-number")
    public ResponseEntity<String> getDetailedReport(@RequestBody MessageReportHeadRequest messageReportHeadRequest){
        ResponseEntity<String> stringResponseEntity = smsService.checkDetailedReport(messageReportHeadRequest);
        return new ResponseEntity<>("Report get successfully for number " + stringResponseEntity.getBody(), HttpStatus.OK);
    }




}