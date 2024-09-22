package com.example.property.mapper.communication;

import com.example.property.dto.communication_request.CommunicationDto;
import com.example.property.dto.property_response.DistrictResponseDto;
import com.example.property.entity.communication.Communication;
import com.example.property.entity.property.DistrictEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface CommunicationMapper {
    CommunicationDto toDTO(Communication entity);

    List<CommunicationDto> toDTOList(List<Communication> entities);

    List<Communication> fromDTOList(List<CommunicationDto> dtoList);

    Communication fromDTO(CommunicationDto dto);

    default Page<CommunicationDto> mapPageEntityToPageResponse(Page<Communication> communications) {
        List<CommunicationDto> dtoList = communications.getContent()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, communications.getPageable(), communications.getTotalElements());
    }
}
