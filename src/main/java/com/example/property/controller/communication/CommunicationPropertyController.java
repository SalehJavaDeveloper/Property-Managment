package com.example.property.controller.communication;

import com.example.property.dto.communication_request.CommunicationDto;
import com.example.property.dto.communication_request.CommunicationPropertyRequestDto;
import com.example.property.dto.communication_response.CommunicationPropertyResponseDto;
import com.example.property.dto.filter.RequestDto;
import com.example.property.handler.SuccessDetails;
import com.example.property.service.property_communication.CommunicationPropertyService;
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
@Tag(name = "Communication Property")
public class CommunicationPropertyController {
    private final CommunicationPropertyService communicationPropertyService;

    @PostMapping("/communication-property")
    @Operation(description = "Save Communication Property operation",
            summary = "This is the summary for save Communication Property",
            responses = {
                    @ApiResponse(description = "Success",responseCode = "200"),
                    @ApiResponse(description = "Unauthorized: Invalid Data",responseCode = "403"),
                    @ApiResponse(description = "Unauthorized: Invalid Data",responseCode = "401")
            })
    ResponseEntity<SuccessDetails<String>> saveCommunicationProperty(@RequestBody @Valid CommunicationPropertyRequestDto communicationPropertyRequestDto){
        communicationPropertyService.saveCommunicationProperty(communicationPropertyRequestDto);
        return ResponseEntity.ok(new SuccessDetails<>("Communication Property save Successfully!", HttpStatus.OK.value(),true));
    }

    @GetMapping("/communication-property")
    @Operation(summary = "Find All Communication Properties")
    ResponseEntity<SuccessDetails<Page<CommunicationPropertyResponseDto>>> findAllCommunicationProperties(@RequestParam(defaultValue = "0") int pageNumber,
                                                                                                          @RequestParam(defaultValue = "10")int pageSize){
        return ResponseEntity.ok(new SuccessDetails<>(communicationPropertyService.findAllCommunicationProperty(pageNumber, pageSize),HttpStatus.OK.value(),true));
    }

    @GetMapping("/communication-property/{communicationPropertyId}")
    @Operation(summary = "Find Communication Properties by id")
    ResponseEntity<SuccessDetails<CommunicationPropertyResponseDto>> findCommunicationPropertyById(@PathVariable("communicationPropertyId") Long communicationPropertyId){
        return ResponseEntity.ok(new SuccessDetails<>(communicationPropertyService.findCommunicationPropertyById(communicationPropertyId),HttpStatus.OK.value(),true));
    }

    @PutMapping("/communication-property")
    @Operation(description = "Update Communication Property operation",
            summary = "This is the summary for update Communication Property",
            responses = {
                    @ApiResponse(description = "Success",responseCode = "200"),
                    @ApiResponse(description = "Unauthorized: Invalid Data",responseCode = "403"),
                    @ApiResponse(description = "Unauthorized: Invalid Data",responseCode = "401")
            })
    ResponseEntity<SuccessDetails<String>> updateCommunicationProperty(@RequestBody @Valid CommunicationPropertyRequestDto communicationPropertyRequestDto){
        communicationPropertyService.updateCommunicationProperty(communicationPropertyRequestDto);
        return ResponseEntity.ok(new SuccessDetails<>("Communication Property update Successfully!", HttpStatus.OK.value(),true));
    }

    @PostMapping("/specification/communication_property")
    @Operation(description = "Specification and filtering into communication_property.")
    ResponseEntity<SuccessDetails<Page<CommunicationPropertyResponseDto>>> getCommunicationPropertyPage(@RequestBody RequestDto requestDto){
        return ResponseEntity.ok(new SuccessDetails<>(communicationPropertyService.getSpecification(requestDto),HttpStatus.OK.value(),true));
    }
}
