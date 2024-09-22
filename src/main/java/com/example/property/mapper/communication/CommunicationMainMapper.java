package com.example.property.mapper.communication;

import com.example.property.dto.communication_request.CommunicationDto;
import com.example.property.dto.communication_request.CommunicationRequestMainDto;
import com.example.property.dto.communication_response.CommunicationResponseMainDto;
import com.example.property.dto.property_response.PropertyResponseDto;
import com.example.property.entity.communication.Communication;
import com.example.property.entity.communication.CommunicationMain;
import com.example.property.entity.property.PropertyEntity;
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
public interface CommunicationMainMapper {
    CommunicationResponseMainDto toDTO(CommunicationMain entity);

    List<CommunicationResponseMainDto> toDTOListm(List<CommunicationMain> entities);

    List<CommunicationMain> fromDTOList(List<CommunicationRequestMainDto> dtoList);

    CommunicationMain fromDTO(CommunicationRequestMainDto dto);


    default Page<CommunicationResponseMainDto> mapPageEntityToPageResponse(Page<CommunicationMain> communicationMains) {
        List<CommunicationResponseMainDto> dtoList = communicationMains.getContent()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, communicationMains.getPageable(), communicationMains.getTotalElements());
    }
}
