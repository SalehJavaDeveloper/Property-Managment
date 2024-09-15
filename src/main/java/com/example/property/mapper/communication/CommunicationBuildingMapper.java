package com.example.property.mapper.communication;

import com.example.property.dto.communication_request.CommunicationBuildingRequestDto;
import com.example.property.dto.communication_response.CommunicationBuildingResponseDto;
import com.example.property.dto.property_response.DistrictResponseDto;
import com.example.property.entity.communication.CommunicationBuilding;
import com.example.property.entity.property.DistrictEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("test")
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface CommunicationBuildingMapper {
    CommunicationBuildingResponseDto toDTO(CommunicationBuilding entity);

    List<CommunicationBuildingResponseDto> toDTOList(List<CommunicationBuilding> entities);

    List<CommunicationBuilding> fromDTOList(List<CommunicationBuildingRequestDto> dtoList);

    CommunicationBuilding fromDTO(CommunicationBuildingRequestDto dto);

    default Page<CommunicationBuildingResponseDto> mapPageEntityToPageResponse(Page<CommunicationBuilding> communicationBuildings) {
        List<CommunicationBuildingResponseDto> dtoList = communicationBuildings.getContent()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, communicationBuildings.getPageable(), communicationBuildings.getTotalElements());
    }
}
