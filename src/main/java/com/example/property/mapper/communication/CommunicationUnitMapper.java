package com.example.property.mapper.communication;

import com.example.property.dto.communication_request.CommunicationUnitRequestDto;
import com.example.property.dto.communication_response.CommunicationPropertyResponseDto;
import com.example.property.dto.communication_response.CommunicationUnitResponseDto;
import com.example.property.entity.communication.CommunicationProperty;
import com.example.property.entity.communication.CommunicationUnit;
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
public interface CommunicationUnitMapper {
    CommunicationUnitResponseDto toDTO(CommunicationUnit entity);

    List<CommunicationUnitResponseDto> toDTOList(List<CommunicationUnit> entities);

    List<CommunicationUnit> fromDTOList(List<CommunicationUnitRequestDto> dtoList);

    CommunicationUnit fromDTO(CommunicationUnitRequestDto dto);

    default Page<CommunicationUnitResponseDto> mapPageEntityToPageResponse(Page<CommunicationUnit> communicationUnits) {
        List<CommunicationUnitResponseDto> dtoList = communicationUnits.getContent()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, communicationUnits.getPageable(), communicationUnits.getTotalElements());
    }
}
