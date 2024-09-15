package com.example.property.service.impl;

import com.example.property.dto.smsDto.report.MessageReportHeadRequest;
import com.example.property.dto.smsDto.report.MessageReportRequest;
import com.example.property.dto.smsDto.report.MessageReportRoot;
import com.example.property.dto.smsDto.send.MessageBodyRequest;
import com.example.property.dto.smsDto.send.MessageHeadRequest;
import com.example.property.dto.smsDto.send.MessageRequest;
import com.example.property.dto.smsDto.send.MessageRequestRoot;
import com.example.property.entity.communication.Communication;
import com.example.property.entity.property.TenantEntity;
import com.example.property.enumuration.MessageStatus;
import com.example.property.repository.communication.CommunicationRepository;
import com.example.property.repository.property.TenantRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MessageService {

    @Value("${credentials.username}")
    private String username;

    @Value("${credentials.password}")
    private String password;

    @Value("${credentials.title}")
    private String title;

    private final String operation = "submit";
    private final String operation2 = "report";
    private final String operation3 = "detailedreport";

    private final String bulk = "true";

    private final TenantRepository tenantRepository;
    private final CommunicationRepository communicationRepository;

    public MessageService(TenantRepository tenantRepository, CommunicationRepository communicationRepository) {
        this.tenantRepository = tenantRepository;
        this.communicationRepository = communicationRepository;
    }

    //Send SMS

    public ResponseEntity<String> sendBulkMessages(MessageHeadRequest messageHeadRequest) {
        String maxId = communicationRepository.findMaxId();

        List<String> results = new ArrayList<>();

        MessageRequestRoot messageRequestRoot = new MessageRequestRoot();
        MessageRequest messageRequest = new MessageRequest();
        MessageHeadRequest messageHead = new MessageHeadRequest();

        List<MessageBodyRequest> messageBodyList = new ArrayList<>();
        messageRequest.setLogin(username);
        messageRequest.setPassword(password);
        messageRequest.setControlid(maxId);
        messageRequest.setOperation(operation);
        messageRequest.setTitle(title);
        messageRequest.setIsbulk(bulk);

        messageHead.setBulkmessage(messageHeadRequest.getBulkmessage());
        messageHead.setScheduled(messageHeadRequest.getScheduled());

        messageRequest.setHead(messageHead);

        List<TenantEntity> tenants = tenantRepository.findAll();

        for (TenantEntity tenantNumber : tenants) {
            MessageBodyRequest messageBody = new MessageBodyRequest();
            messageBody.setMsisdn(tenantNumber.getPhoneNumber1());
            messageBodyList.add(messageBody);
        }

        messageRequest.setBody(messageBodyList);
        messageRequestRoot.setRequest(messageRequest);
        ResponseEntity<String> stringResponseEntity = sendSingleMessage(messageRequestRoot);

        for (String item : results) {
            results.add("Result for " + item + " " + stringResponseEntity.getBody());
        }

        return new ResponseEntity<>(stringResponseEntity.getBody(), HttpStatus.OK);
    }

    public ResponseEntity<String> sendSingleMessage(MessageRequestRoot messageRequestRoot) {
        String url = "https://www.sendsms.az/smxml/api";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<MessageRequestRoot> requestEntity = new HttpEntity<>(messageRequestRoot, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        System.out.println("Response for msisdn " + messageRequestRoot.getRequest().getBody() + ": " + responseEntity.getBody());

        String smsCode = extractTaskId(responseEntity.getBody());
        String responseCode = extractResponseCode(responseEntity.getBody());

        Communication communication = new Communication();
        communication.setTaskId(smsCode);
        assert responseCode != null;
        if (responseCode.equals("000")) {
            communication.setMessageStatus(MessageStatus.SUCCESSFUL);
        } else {
            communication.setMessageStatus(MessageStatus.FAILED);
        }

        communicationRepository.save(communication);

        return responseEntity;
    }


    // Extract TaskId

    private static String extractTaskId(String response) {
        String taskIdPattern = "\"taskid\":\\s*(\\d+)";

        Pattern pattern = Pattern.compile(taskIdPattern);
        Matcher matcher = pattern.matcher(response);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    //Extarct Response Code

    private static String extractResponseCode(String response) {
        String responseCodePattern = "\"responsecode\":\\s*\"(\\d+)\"";

        Pattern pattern = Pattern.compile(responseCodePattern);
        Matcher matcher = pattern.matcher(response);

        if (matcher.find()) {
            return matcher.group(1);
        }

        return null;
    }

    //Report

    public ResponseEntity<String> checkReport(MessageReportHeadRequest headRequest) {
        MessageReportRequest messageReportRequest = new MessageReportRequest();
        MessageReportHeadRequest messageReportHeadRequest = new MessageReportHeadRequest();
        MessageReportRoot messageReportRoot = new MessageReportRoot();

        messageReportHeadRequest.setTaskid(headRequest.getTaskid());

        messageReportRequest.setLogin(username);
        messageReportRequest.setPassword(password);
        messageReportRequest.setOperation(operation2);
        messageReportRequest.setMessageReportHeadRequest(messageReportHeadRequest);

        messageReportRoot.setMessageReportRequest(messageReportRequest);

        String url = "https://www.sendsms.az/smxml/api";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<MessageReportRoot> requestEntity = new HttpEntity<>(messageReportRoot, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        System.out.println("Message Info for " + responseEntity.getBody());
        return responseEntity;
    }


    //Detailed Report

    public ResponseEntity<String> checkDetailedReport(MessageReportHeadRequest headRequest) {
        MessageReportRequest messageReportRequest = new MessageReportRequest();
        MessageReportHeadRequest messageReportHeadRequest = new MessageReportHeadRequest();
        MessageReportRoot messageReportRoot = new MessageReportRoot();

        messageReportHeadRequest.setTaskid(headRequest.getTaskid());

        messageReportRequest.setLogin(username);
        messageReportRequest.setPassword(password);
        messageReportRequest.setOperation(operation3);
        messageReportRequest.setMessageReportHeadRequest(messageReportHeadRequest);

        messageReportRoot.setMessageReportRequest(messageReportRequest);

        String url = "https://www.sendsms.az/smxml/api";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<MessageReportRoot> requestEntity = new HttpEntity<>(messageReportRoot, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        System.out.println("Message Info for " + responseEntity.getBody());
        return responseEntity;
    }
}