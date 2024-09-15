package com.example.property.mapper.role;

import com.example.property.dto.role_request.CompanyRequestDto;
import com.example.property.dto.role_request.PacketRequestDto;
import com.example.property.dto.role_response.CompanyResponseDto;
import com.example.property.dto.role_response.PacketResponseDto;
import com.example.property.entity.companyRoles.Company;
import com.example.property.entity.companyRoles.Packet;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface PacketMapper {

    PacketResponseDto toDTO(Packet entity);
    List<PacketResponseDto> toDTOList(List<Packet> entities);
    List<Packet> fromDTOList(List<PacketRequestDto> dtoList);

    Packet fromDTO(PacketRequestDto dto);
}
