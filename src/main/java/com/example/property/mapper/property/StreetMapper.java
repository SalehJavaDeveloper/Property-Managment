package com.example.property.mapper.property;


import com.example.property.dto.property_request.StreetRequestDto;
import com.example.property.dto.property_response.StreetResponseDto;
import com.example.property.entity.property.StreetEntity;
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
public interface StreetMapper {

    StreetResponseDto toDTO(StreetEntity entity);
    List<StreetResponseDto> toDTOList(List<StreetEntity> entities);

    List<StreetEntity> fromDTOList(List<StreetRequestDto> dtoList);

    StreetEntity fromDTO(StreetRequestDto dto);

    default Page<StreetResponseDto> mapPageEntityToPageResponse(Page<StreetEntity> streetEntities) {
        List<StreetResponseDto> dtoList = streetEntities.getContent()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, streetEntities.getPageable(), streetEntities.getTotalElements());
    }
}
