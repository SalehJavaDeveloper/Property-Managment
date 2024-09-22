package com.example.property.service.message;

import com.example.property.dto.smsDto.report.MessageReportHeadRequest;
import com.example.property.dto.smsDto.report.MessageReportRequest;
import com.example.property.dto.smsDto.report.MessageReportRoot;
import com.example.property.dto.smsDto.send.MessageBodyRequest;
import com.example.property.dto.smsDto.send.MessageHeadRequest;
import com.example.property.dto.smsDto.send.MessageRequest;
import com.example.property.dto.smsDto.send.MessageRequestRoot;
import com.example.property.entity.communication.Communication;
import com.example.property.entity.communication.CommunicationBuilding;
import com.example.property.entity.communication.CommunicationProperty;
import com.example.property.entity.communication.CommunicationUnit;
import com.example.property.entity.property.BuildingEntity;
import com.example.property.entity.property.PropertyEntity;
import com.example.property.entity.property.TenantEntity;
import com.example.property.entity.property.UnitEntity;
import com.example.property.enumuration.MessageStatus;
import com.example.property.repository.communication.CommunicationBuildingRepository;
import com.example.property.repository.communication.CommunicationPropertyRepository;
import com.example.property.repository.communication.CommunicationRepository;
import com.example.property.repository.communication.CommunicationUnitRepository;
import com.example.property.repository.property.BuildingRepository;
import com.example.property.repository.property.PropertyRepository;
import com.example.property.repository.property.TenantRepository;
import com.example.property.repository.property.UnitRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SMSService {
    @Value("${credentials.username}")
    private String username;

    @Value("${credentials.password}")
    private String password;

    private final String operation = "submit";
    private final String operation2 = "report";
    private final String operation3 = "detailedreport";
    private final String bulk = "true";

    private final TenantRepository tenantRepository;
    private final CommunicationRepository communicationRepository;
    private final PropertyRepository propertyRepository;
    private final BuildingRepository buildingRepository;
    private final UnitRepository unitRepository;
    private final CommunicationPropertyRepository communicationPropertyRepository;
    private final CommunicationBuildingRepository communicationBuildingRepository;
    private final CommunicationUnitRepository communicationUnitRepository;

    public SMSService(TenantRepository tenantRepository, CommunicationRepository communicationRepository, PropertyRepository propertyRepository, BuildingRepository buildingRepository, UnitRepository unitRepository, CommunicationPropertyRepository communicationPropertyRepository, CommunicationBuildingRepository communicationBuildingRepository, CommunicationUnitRepository communicationUnitRepository) {
        this.tenantRepository = tenantRepository;
        this.communicationRepository = communicationRepository;
        this.propertyRepository = propertyRepository;
        this.buildingRepository = buildingRepository;
        this.unitRepository = unitRepository;
        this.communicationPropertyRepository = communicationPropertyRepository;
        this.communicationBuildingRepository = communicationBuildingRepository;
        this.communicationUnitRepository = communicationUnitRepository;
    }

    MessageRequestRoot messageRequestRoot = new MessageRequestRoot();
    MessageRequest messageRequest = new MessageRequest();
    MessageHeadRequest messageHead = new MessageHeadRequest();
    List<Communication> allByDeliveryDate;

    // Message For Communication
    public List<TenantEntity> messageFromCommunication() {
        List<TenantEntity> tenantEntities = new ArrayList<>();
        List<CommunicationBuilding> communicationBuildings = new ArrayList<>();
        List<CommunicationUnit> communicationUnits = new ArrayList<>();
        List<CommunicationProperty> communicationProperties = new ArrayList<>();
        List<BuildingEntity> buildingEntities = new ArrayList<>();
        List<BuildingEntity> buildingEntitiesCheck = new ArrayList<>();
        List<UnitEntity> unitEntitiesCheck = new ArrayList<>();
        List<UnitEntity> unitEntities = new ArrayList<>();
        Set<PropertyEntity> propertyEntities = new HashSet<>();
        allByDeliveryDate =new ArrayList<>();

        LocalDate today = LocalDate.now();
        allByDeliveryDate = communicationRepository.findAllByDeliveryDate(today);

        for (Communication item : allByDeliveryDate) {
            communicationProperties.addAll(item.getCommunicationProperties());
            communicationBuildings.addAll(item.getCommunicationBuildings());
            communicationUnits.addAll(item.getCommunicationUnits());
        }

        for (CommunicationProperty communicationProperty : communicationProperties) {
//            propertyEntities.add(communicationProperty.getPropertyEntity());
            List<PropertyEntity> propertyEntities1 = communicationProperty.getPropertyEntities();


            for (PropertyEntity item: propertyEntities1){
                propertyEntities.add(item);

            }
        }

        for (CommunicationBuilding communicationBuilding : communicationBuildings) {
//            buildingEntities.add(communicationBuilding.getBuildingEntity());
            BuildingEntity building = buildingRepository.findById(communicationBuilding.getId()).orElseThrow();
            buildingEntities.add(building);
        }

        for (CommunicationUnit communicationUnit : communicationUnits) {
            unitEntities.add(communicationUnit.getUnitEntity());
        }

        for (PropertyEntity properties : propertyEntities) {
            PropertyEntity propertyEntityByName = propertyRepository.findById(properties.getId()).orElseThrow();
            buildingEntitiesCheck = propertyEntityByName.getBuildingEntities();

            buildingEntities.addAll(buildingEntitiesCheck);
        }

        for (BuildingEntity building : buildingEntities) {
            unitEntitiesCheck.addAll(building.getUnitEntities());

        }

        unitEntities.addAll(unitEntitiesCheck);

        for (UnitEntity unit : unitEntities) {
            tenantEntities.addAll(unit.getTenantEntities());
        }

        tenantEntities.forEach(System.out::println);

        return tenantEntities;
    }

    //Send SMS
//    @Scheduled(fixedRate = 15 * 60 * 1000 )
    public ResponseEntity<String> sendBulkMessages(MessageHeadRequest messageHeadRequest) {
        //Generate ControlId
        Random random = new Random();
        int i = random.nextInt(10, 999);
        String maxId = communicationRepository.findMaxId();
        maxId = maxId + i;

        List<String> results = new ArrayList<>();

        List<MessageBodyRequest> messageBodyList = new ArrayList<>();
        messageRequest.setLogin(username);
        messageRequest.setPassword(password);
        messageRequest.setControlid(maxId);
        messageRequest.setOperation(operation);
        messageRequest.setIsbulk(bulk);

        messageHead.setBulkmessage(messageHeadRequest.getBulkmessage());
        messageHead.setScheduled(messageHeadRequest.getScheduled());
        messageHead.setTitle(messageHeadRequest.getTitle());

        messageRequest.setHead(messageHead);

        List<TenantEntity> tenantEntitiesCheck = new ArrayList<>();

        List<TenantEntity> tenantEntities = messageFromCommunication();
        tenantEntitiesCheck.addAll(tenantEntities);

        for (TenantEntity tenant : tenantEntitiesCheck) {
            if (tenant.getSmsPhoneNumber1().equals(true) && tenant.getActivate().equals(true)) {
                MessageBodyRequest messageBody = new MessageBodyRequest();
                messageBody.setMsisdn(tenant.getPhoneNumber1());
                messageBodyList.add(messageBody);
            }
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

        for (Communication communication : allByDeliveryDate) {
            communication.setTaskId(smsCode);

            assert responseCode != null;
            communication.setTaskId(smsCode);
            if ("000".equals(responseCode)) {
                communication.setMessageStatus(MessageStatus.SUCCESSFUL);
            } else {
                communication.setMessageStatus(MessageStatus.FAILED);
            }
            communicationRepository.save(communication);
        }

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
