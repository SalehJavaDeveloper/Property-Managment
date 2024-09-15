package com.example.property.controller.communication;

import com.example.property.dto.communication_request.CommunicationBuildingRequestDto;
import com.example.property.dto.communication_request.CommunicationDto;
import com.example.property.dto.communication_response.CommunicationBuildingResponseDto;
import com.example.property.dto.filter.RequestDto;
import com.example.property.handler.SuccessDetails;
import com.example.property.service.property_communication.CommunicationBuildingService;
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
@Tag(name = "Communication Building")
public class CommunicationBuildingController {

    private final CommunicationBuildingService communicationBuildingService;

    @PostMapping("/communication-building")
    @Operation(description = "Save Communication Building operation",
            summary = "This is the summary for Save Communication Building",
            responses = {
                    @ApiResponse(description = "Success",responseCode = "200"),
                    @ApiResponse(description = "Unauthorized: Invalid Data",responseCode = "403"),
                    @ApiResponse(description = "Unauthorized: Invalid Data",responseCode = "401")
            })
    ResponseEntity<SuccessDetails<String>> saveCommunicationBuilding(@RequestBody @Valid CommunicationBuildingRequestDto communicationBuildingRequestDto){
        communicationBuildingService.saveCommunicationBuilding(communicationBuildingRequestDto);
        return ResponseEntity.ok(new SuccessDetails<>("Communication Building save Successfully!", HttpStatus.OK.value(),true));
    }

    @GetMapping("/communication-building")
    @Operation(summary = "Find All Communication Buildings")
    ResponseEntity<SuccessDetails<Page<CommunicationBuildingResponseDto>>> findAllCommunicationBuildings(@RequestParam(defaultValue = "0") int pageNumber,
                                                                                                         @RequestParam(defaultValue = "10")int pageSize){
        return ResponseEntity.ok(new SuccessDetails<>(communicationBuildingService.findAllCommunicationBuilding(pageNumber, pageSize),HttpStatus.OK.value(),true));
    }
    @GetMapping("/communication-building/{communicationBuildingId}")
    @Operation(summary = "Find Communication Buildings by id")
    ResponseEntity<SuccessDetails<CommunicationBuildingResponseDto>> findCommunicationBuildingById(@PathVariable("communicationBuildingId") Long communicationBuildingId){
        return ResponseEntity.ok(new SuccessDetails<>(communicationBuildingService.findCommunicationBuildingById(communicationBuildingId),HttpStatus.OK.value(),true));
    }
    @PutMapping("/communication-building")
    @Operation(description = "Update Communication Building operation",
            summary = "This is the summary for Update Communication Building",
            responses = {
                    @ApiResponse(description = "Success",responseCode = "200"),
                    @ApiResponse(description = "Unauthorized: Invalid Data",responseCode = "403"),
                    @ApiResponse(description = "Unauthorized: Invalid Data",responseCode = "401")
            })
    ResponseEntity<SuccessDetails<String>> updateCommunicationBuilding(@RequestBody @Valid CommunicationBuildingRequestDto communicationBuildingRequestDto){
        communicationBuildingService.updateCommunicationBuilding(communicationBuildingRequestDto);
        return ResponseEntity.ok(new SuccessDetails<>("Communication Building update Successfully!", HttpStatus.OK.value(),true));
    }

    @PostMapping("/specification/communication_building")
    @Operation(description = "Specification and filtering into communication_building.")
    ResponseEntity<SuccessDetails<Page<CommunicationBuildingResponseDto>>> getCommunicationBuildingPage(@RequestBody RequestDto requestDto){
        return ResponseEntity.ok(new SuccessDetails<>(communicationBuildingService.getSpecification(requestDto),HttpStatus.OK.value(),true));
    }
}
