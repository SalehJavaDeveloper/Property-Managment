package com.example.property.controller.communication;

import com.example.property.dto.communication_request.CommunicationDto;
import com.example.property.dto.filter.RequestDto;
import com.example.property.dto.property_response.PropertyResponseDto;
import com.example.property.handler.SuccessDetails;
import com.example.property.service.property_communication.CommunicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Tag(name = "Communication")
public class CommunicationController {

    private final CommunicationService communicationService;

    @PostMapping("/communication")
    @Operation(description = "Save Communication operation",
            summary = "This is the summary for save Communication",
            responses = {
                    @ApiResponse(description = "Success",responseCode = "200"),
                    @ApiResponse(description = "Unauthorized: Invalid Data",responseCode = "403"),
                    @ApiResponse(description = "Unauthorized: Invalid Data",responseCode = "401")
            })
    ResponseEntity<SuccessDetails<String>> saveCommunication(@RequestBody @Valid CommunicationDto communicationDto){
        communicationService.saveCommunication(communicationDto);
        return ResponseEntity.ok(new SuccessDetails<>("Communication Save Successfully!", HttpStatus.OK.value(),true));
    }

    @GetMapping("/communication")
    @Operation(summary = "Find All Communications")
    ResponseEntity<SuccessDetails<Page<CommunicationDto>>> findAllCommunications(@RequestParam(defaultValue = "0") int pageNumber,
                                                                                 @RequestParam(defaultValue = "10")int pageSize){
        return ResponseEntity.ok(new SuccessDetails<>(communicationService.findAllCommunication(pageNumber, pageSize),HttpStatus.OK.value(),true));
    }
    @GetMapping("/communication/{communicationId}")
    @Operation(summary = "Find Communication By Id")
    ResponseEntity<SuccessDetails<CommunicationDto>> findCommunicationById(@PathVariable("communicationId") Long communicationId){
        return ResponseEntity.ok(new SuccessDetails<>(communicationService.findCommunicationById(communicationId),HttpStatus.OK.value(),true));
    }
    @PutMapping("/communication")
    @Operation(description = "Update Communication operation",
            summary = "This is the summary for update Communication",
            responses = {
                    @ApiResponse(description = "Success",responseCode = "200"),
                    @ApiResponse(description = "Unauthorized: Invalid Data",responseCode = "403"),
                    @ApiResponse(description = "Unauthorized: Invalid Data",responseCode = "401")
            })
    ResponseEntity<SuccessDetails<String>> updateCommunication(@RequestBody @Valid CommunicationDto communicationDto){
        communicationService.updateCommunication(communicationDto);
        return ResponseEntity.ok(new SuccessDetails<>("Communication Update Successfully!", HttpStatus.OK.value(),true));
    }

    @PostMapping("/specification/communication")
    @Operation(description = "Specification and filtering into communication.")
    ResponseEntity<SuccessDetails<Page<CommunicationDto>>> getCommunicationPage(@RequestBody RequestDto requestDto){
        return ResponseEntity.ok(new SuccessDetails<>(communicationService.getSpecification(requestDto),HttpStatus.OK.value(),true));
    }

}
