package com.example.property.mapper.communication;

import com.example.property.dto.communication_request.CommunicationPropertyRequestDto;
import com.example.property.dto.communication_response.CommunicationPropertyResponseDto;
import com.example.property.dto.property_response.DistrictResponseDto;
import com.example.property.entity.communication.CommunicationProperty;
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
public interface CommunicationPropertyMapper {

    CommunicationPropertyResponseDto toDTO(CommunicationProperty entity);

    List<CommunicationPropertyResponseDto> toDTOList(List<CommunicationProperty> entities);

    List<CommunicationProperty> fromDTOList(List<CommunicationPropertyRequestDto> dtoList);

    CommunicationProperty fromDTO(CommunicationPropertyRequestDto dto);

    default Page<CommunicationPropertyResponseDto> mapPageEntityToPageResponse(Page<CommunicationProperty> communicationProperties) {
        List<CommunicationPropertyResponseDto> dtoList = communicationProperties.getContent()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, communicationProperties.getPageable(), communicationProperties.getTotalElements());
    }
}
