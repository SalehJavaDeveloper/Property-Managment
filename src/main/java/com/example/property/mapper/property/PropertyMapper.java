package com.example.property.mapper.property;

import com.example.property.dto.property_request.PropertyRequestDto;
import com.example.property.dto.property_response.DistrictResponseDto;
import com.example.property.dto.property_response.PropertyResponseDto;
import com.example.property.entity.property.DistrictEntity;
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
public interface PropertyMapper {
    PropertyResponseDto toDTO(PropertyEntity entity);

    List<PropertyResponseDto> toDTOList(List<PropertyEntity> entities);

    List<PropertyEntity> fromDTOList(List<PropertyRequestDto> dtoList);

    PropertyEntity fromDTO(PropertyRequestDto dto);

    default Page<PropertyResponseDto> mapPageEntityToPageResponse(Page<PropertyEntity> propertyEntities) {
        List<PropertyResponseDto> dtoList = propertyEntities.getContent()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, propertyEntities.getPageable(), propertyEntities.getTotalElements());
    }

}
