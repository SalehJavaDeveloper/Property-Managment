package com.example.property.controller.communication;

import com.example.property.dto.communication_request.CommunicationRequestMainDto;
import com.example.property.dto.communication_response.CommunicationResponseMainDto;
import com.example.property.dto.filter.RequestDto;
import com.example.property.enumuration.AllMessageStatus;
import com.example.property.handler.SuccessDetails;
import com.example.property.service.property_communication.CommunicationMainService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Tag(name = "CommunicationMainController")
public class CommunicationMainController {

    private final CommunicationMainService communicationMainService;

    @PostMapping("/main/communication")
    @Operation(summary = "Save Communications")
    ResponseEntity<SuccessDetails<String>> saveCommunicationMain(@RequestBody CommunicationRequestMainDto communicationRequestMainDto){
        communicationMainService.saveCommunicationMain(communicationRequestMainDto);
        return ResponseEntity.ok(new SuccessDetails<>("Communication save Successfully!", HttpStatus.OK.value(),true));
    }

    @PutMapping("/main/communication")
    @Operation(summary = "Update Communications")
    ResponseEntity<SuccessDetails<String>> updateCommunicationMain(@RequestBody CommunicationRequestMainDto communicationRequestMainDto){
        communicationMainService.updateCommunication(communicationRequestMainDto);
        return ResponseEntity.ok(new SuccessDetails<>("Communication update Successfully!", HttpStatus.OK.value(),true));
    }

    @GetMapping("/main/communication/{id}/{messageStatus}")
    @Operation(summary = "Cancel Communications")
    ResponseEntity<SuccessDetails<String>> cancelCommunication(@PathVariable("id") Long id, @PathVariable("messageStatus") AllMessageStatus messageStatus){
        communicationMainService.cancelCommunication(id, messageStatus);
        return ResponseEntity.ok(new SuccessDetails<>("Communication canceled Successfully!", HttpStatus.OK.value(),true));
    }

    @PostMapping("/main/communication/specification")
    @Operation(summary = "Get all Communication data with Specification")
    ResponseEntity<SuccessDetails<Page<CommunicationResponseMainDto>>>specificationCommunication(@RequestBody RequestDto requestDto){
        return ResponseEntity.ok(new SuccessDetails<>(communicationMainService.getSpecification(requestDto),HttpStatus.OK.value(),true));
    }
}
