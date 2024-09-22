package com.example.property.mapper.property;

import com.example.property.dto.property_request.CityRequestDto;
import com.example.property.dto.property_response.CityResponseDto;
import com.example.property.entity.property.CityEntity;
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
public interface CityMapper {

    CityResponseDto toDTO(CityEntity entity);

    List<CityResponseDto> toDTOList(List<CityEntity> entities);

    List<CityEntity> fromDTOList(List<CityRequestDto> dtoList);

    CityEntity fromDTO(CityRequestDto dto);

    default Page<CityResponseDto> mapPageEntityToPageResponse(Page<CityEntity> cityEntities) {
        List<CityResponseDto> dtoList = cityEntities.getContent()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, cityEntities.getPageable(), cityEntities.getTotalElements());
    }
}
