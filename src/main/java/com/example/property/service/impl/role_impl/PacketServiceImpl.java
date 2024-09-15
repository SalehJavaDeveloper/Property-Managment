package com.example.property.service.impl.role_impl;

import com.example.property.dto.role_request.PacketRequestDto;
import com.example.property.dto.role_response.PacketResponseDto;
import com.example.property.entity.companyRoles.Packet;
import com.example.property.exception.MethodArgumentNotValidException;
import com.example.property.mapper.role.PacketMapper;
import com.example.property.repository.role.CompanyRepository;
import com.example.property.repository.role.PacketRepository;
import com.example.property.service.role.PacketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PacketServiceImpl implements PacketService {

    private final PacketRepository packetRepository;

    private final PacketMapper packetMapper;
    private final CompanyRepository companyRepository;
    @Override
    public void savePacket(PacketRequestDto packetRequestDto) {
        if(packetRequestDto == null){
            throw new MethodArgumentNotValidException(("Data Arguments is not valid!"));
        }
        Packet packet = packetMapper.fromDTO(packetRequestDto);
        packet.setCompany(companyRepository.getReferenceById(packetRequestDto.getCompanyId()));
        packetRepository.save(packet);
    }

    @Override
    public PacketResponseDto findPacketById(Long id) {
        if(packetRepository.findById(id).isEmpty()){
            throw new MethodArgumentNotValidException("Data not found with specified Packet!");
        }
        return packetMapper.toDTO(packetRepository.getReferenceById(id));
    }

    @Override
    public List<PacketResponseDto> findAllPackets() {
        return packetMapper.toDTOList(packetRepository.findAll());
    }
}
