package com.example.property.service.role;

import com.example.property.dto.role_request.PacketRequestDto;
import com.example.property.dto.role_response.PacketResponseDto;

import java.util.List;

public interface PacketService {
     void savePacket(PacketRequestDto packetRequestDto);

     PacketResponseDto findPacketById(Long id);

     List<PacketResponseDto> findAllPackets();

}
