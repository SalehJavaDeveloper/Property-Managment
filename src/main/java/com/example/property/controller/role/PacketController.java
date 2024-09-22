package com.example.property.controller.role;

import com.example.property.dto.role_request.PacketRequestDto;
import com.example.property.dto.role_response.PacketResponseDto;
import com.example.property.handler.SuccessDetails;
import com.example.property.service.role.PacketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Tag(name = "Packet")
public class PacketController {

    private final PacketService packetService;

    @PostMapping("/packet")
    @Operation(description = "Save Packet operation",
            summary = "This is the summary for save Packet data",
            responses = {
                    @ApiResponse(description = "Success",responseCode = "200"),
                    @ApiResponse(description = "Unauthorized: Invalid Token",responseCode = "403")
            })
    public ResponseEntity<SuccessDetails<String>> savePacket(@RequestBody PacketRequestDto packetRequestDto){
        packetService.savePacket(packetRequestDto);
        return ResponseEntity.ok(new SuccessDetails<>("Packet save successFully!", HttpStatus.OK.value(), true));
    }

    @GetMapping("/packet/{packetId}")
    @Operation(summary = "Find packets by id")
    public ResponseEntity<SuccessDetails<PacketResponseDto>> getPacketById(@PathVariable("packetId") Long id){
        return ResponseEntity.ok(new SuccessDetails<>(packetService.findPacketById(id),HttpStatus.OK.value(),true));
    }

    @GetMapping("/packet")
    @Operation(summary = "Find all Packets")
    public ResponseEntity<SuccessDetails<List<PacketResponseDto>>> findAllPackets(){
        return ResponseEntity.ok(new SuccessDetails<>(packetService.findAllPackets(),HttpStatus.OK.value(),true));
    }
}
