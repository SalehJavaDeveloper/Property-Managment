package com.example.property.mapper.communication;

import com.example.property.dto.communication_request.CommunicationChannelRequestDto;
import com.example.property.dto.communication_response.CommunicationChannelResponseDto;
import com.example.property.dto.property_response.DistrictResponseDto;
import com.example.property.entity.communication.CommunicationChannel;
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
public interface CommunicationChannelMapper {

    CommunicationChannelResponseDto toDTO(CommunicationChannel entity);

    List<CommunicationChannelResponseDto> toDTOList(List<CommunicationChannel> entities);

    List<CommunicationChannel> fromDTOList(List<CommunicationChannelRequestDto> dtoList);

    CommunicationChannel fromDTO(CommunicationChannelRequestDto dto);

    default Page<CommunicationChannelResponseDto> mapPageEntityToPageResponse(Page<CommunicationChannel> communicationChannels) {
        List<CommunicationChannelResponseDto> dtoList = communicationChannels.getContent()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, communicationChannels.getPageable(), communicationChannels.getTotalElements());
    }
}
