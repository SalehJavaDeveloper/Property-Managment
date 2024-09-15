package com.example.property.controller.communication;

import com.example.property.dto.communication_request.CommunicationChannelRequestDto;
import com.example.property.dto.communication_request.CommunicationDto;
import com.example.property.dto.communication_response.CommunicationChannelResponseDto;
import com.example.property.dto.filter.RequestDto;
import com.example.property.handler.SuccessDetails;
import com.example.property.service.property_communication.CommunicationChannelService;
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
@Tag(name = "Communication Channel")
public class CommunicationChannelController {

    private final CommunicationChannelService communicationChannelService;

    @PostMapping("/communication-channel")
    @Operation(description = "Save Communication Channel operation",
            summary = "This is the summary for save Communication Channel",
            responses = {
                    @ApiResponse(description = "Success",responseCode = "200"),
                    @ApiResponse(description = "Unauthorized: Invalid Data",responseCode = "403"),
                    @ApiResponse(description = "Unauthorized: Invalid Data",responseCode = "401")
            })
    ResponseEntity<SuccessDetails<String>> saveCommunicationChannel(@RequestBody @Valid CommunicationChannelRequestDto communicationChannelRequestDto){
        communicationChannelService.saveCommunicationChannel(communicationChannelRequestDto);
        return ResponseEntity.ok(new SuccessDetails<>("CommunicationChannel save Successfully!", HttpStatus.OK.value(),true));
    }

    @GetMapping("/communication-channel")
    @Operation(summary = "Find All Communication Channels")
    ResponseEntity<SuccessDetails<Page<CommunicationChannelResponseDto>>> findAllCommunicationChannels(@RequestParam(defaultValue = "0") int pageNumber,
                                                                                                       @RequestParam(defaultValue = "10")int pageSize){
        return ResponseEntity.ok(new SuccessDetails<>(communicationChannelService.findAllCommunicationChannel(pageNumber, pageSize),HttpStatus.OK.value(),true));
    }

    @GetMapping("/communication-channel/{communicationChannelId}")
    @Operation(summary = "Find Communication Channel by id")
    ResponseEntity<SuccessDetails<CommunicationChannelResponseDto>> findAllCommunicationChannelById(@PathVariable("communicationChannelId") Long communicationChannelId){
        return ResponseEntity.ok(new SuccessDetails<>(communicationChannelService.findCommunicationChannelById(communicationChannelId),HttpStatus.OK.value(),true));
    }
    @PutMapping("/communication-channel")
    @Operation(description = "Update Communication Channel operation",
            summary = "This is the summary for update Communication Channel",
            responses = {
                    @ApiResponse(description = "Success",responseCode = "200"),
                    @ApiResponse(description = "Unauthorized: Invalid Data",responseCode = "403"),
                    @ApiResponse(description = "Unauthorized: Invalid Data",responseCode = "401")
            })
    ResponseEntity<SuccessDetails<String>> updateCommunicationChannel(@RequestBody @Valid CommunicationChannelRequestDto communicationChannelRequestDto){
        communicationChannelService.updateCommunicationChannel(communicationChannelRequestDto);
        return ResponseEntity.ok(new SuccessDetails<>("CommunicationChannel update Successfully!", HttpStatus.OK.value(),true));
    }

    @PostMapping("/specification/communication_channel")
    @Operation(description = "Specification and filtering into communication_channel.")
    ResponseEntity<SuccessDetails<Page<CommunicationChannelResponseDto>>> getCommunicationChannelPage(@RequestBody RequestDto requestDto){
        return ResponseEntity.ok(new SuccessDetails<>(communicationChannelService.getSpecification(requestDto),HttpStatus.OK.value(),true));
    }

}
