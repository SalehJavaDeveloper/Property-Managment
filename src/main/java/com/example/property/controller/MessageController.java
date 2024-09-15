package com.example.property.controller;

import com.example.property.dto.smsDto.report.MessageReportHeadRequest;
import com.example.property.dto.smsDto.report.MessageReportRequest;
import com.example.property.dto.smsDto.send.MessageHeadRequest;
import com.example.property.service.impl.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/message")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }


    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody MessageHeadRequest messageHeadRequest) {
        ResponseEntity<String> stringResponseEntity = messageService.sendBulkMessages(messageHeadRequest);
//        List<String> responseBody = new ArrayList<>();
//        responseBody.addAll(stringResponseEntity.getBody());
        return new ResponseEntity<>(stringResponseEntity.getBody(), HttpStatus.OK);
    }

    @PostMapping("/report-number")
    public ResponseEntity<String> getReport(@RequestBody MessageReportHeadRequest messageReportHeadRequest){
        ResponseEntity<String> stringResponseEntity = messageService.checkReport(messageReportHeadRequest);
        return new ResponseEntity<>("Report get successfully for number " + stringResponseEntity.getBody(), HttpStatus.OK);
    }

    @PostMapping("/detailed-report-number")
    public ResponseEntity<String> getDetailedReport(@RequestBody MessageReportHeadRequest messageReportHeadRequest){
        ResponseEntity<String> stringResponseEntity = messageService.checkDetailedReport(messageReportHeadRequest);
        return new ResponseEntity<>("Report get successfully for number " + stringResponseEntity.getBody(), HttpStatus.OK);
    }




}