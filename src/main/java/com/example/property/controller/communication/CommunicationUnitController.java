package com.example.property.controller.communication;

import com.example.property.dto.communication_request.CommunicationDto;
import com.example.property.dto.communication_request.CommunicationUnitRequestDto;
import com.example.property.dto.communication_response.CommunicationUnitResponseDto;
import com.example.property.dto.filter.RequestDto;
import com.example.property.handler.SuccessDetails;
import com.example.property.service.property_communication.CommunicationUnitService;
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
@Tag(name = "Communication Unit")
public class CommunicationUnitController {

    private final CommunicationUnitService communicationUnitService;

    @PostMapping("/communication-unit")
    @Operation(description = "Save Communication Unit operation",
            summary = "This is the summary for save Communication Unit",
            responses = {
                    @ApiResponse(description = "Success",responseCode = "200"),
                    @ApiResponse(description = "Unauthorized: Invalid Data",responseCode = "403"),
                    @ApiResponse(description = "Unauthorized: Invalid Data",responseCode = "401")
            })
    ResponseEntity<SuccessDetails<String>> saveCommunicationUnit(@RequestBody @Valid CommunicationUnitRequestDto communicationUnitRequestDto){
        communicationUnitService.saveCommunicationUnit(communicationUnitRequestDto);
        return ResponseEntity.ok(new SuccessDetails<>("Communication Unit save Successfully!", HttpStatus.OK.value(),true));
    }

    @GetMapping("/communication-unit")
    @Operation(summary = "Find All Communication Units")
    ResponseEntity<SuccessDetails<Page<CommunicationUnitResponseDto>>> findAllCommunicationUnits(@RequestParam(defaultValue = "0") int pageNumber,
                                                                                                 @RequestParam(defaultValue = "10")int pageSize){
        return ResponseEntity.ok(new SuccessDetails<>(communicationUnitService.findAllCommunicationUnits(pageNumber, pageSize),HttpStatus.OK.value(),true));
    }
    @GetMapping("/communication-unit/{communicationUnitId}")
    @Operation(summary = "Find Communication Units by id")
    ResponseEntity<SuccessDetails<CommunicationUnitResponseDto>> findCommunicationUnitById(@PathVariable("communicationUnitId") Long communicationUnitId){
        return ResponseEntity.ok(new SuccessDetails<>(communicationUnitService.findCommunicationUnitById(communicationUnitId),HttpStatus.OK.value(),true));
    }

    @PutMapping("/communication-unit")
    @Operation(description = "Update Communication Unit operation",
            summary = "This is the summary for update Communication Unit",
            responses = {
                    @ApiResponse(description = "Success",responseCode = "200"),
                    @ApiResponse(description = "Unauthorized: Invalid Data",responseCode = "403"),
                    @ApiResponse(description = "Unauthorized: Invalid Data",responseCode = "401")
            })
    ResponseEntity<SuccessDetails<String>> updateCommunicationUnit(@RequestBody @Valid CommunicationUnitRequestDto communicationUnitRequestDto){
        communicationUnitService.updateCommunicationUnit(communicationUnitRequestDto);
        return ResponseEntity.ok(new SuccessDetails<>("Communication Unit update Successfully!", HttpStatus.OK.value(),true));
    }

    @PostMapping("/specification/communication_unit")
    @Operation(description = "Specification and filtering into communication_unit.")
    ResponseEntity<SuccessDetails<Page<CommunicationUnitResponseDto>>> getCommunicationUnitPage(@RequestBody RequestDto requestDto){
        return ResponseEntity.ok(new SuccessDetails<>(communicationUnitService.getSpecification(requestDto),HttpStatus.OK.value(),true));
    }
}
