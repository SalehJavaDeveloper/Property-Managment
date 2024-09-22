package com.example.property.mapper.property;

import com.example.property.dto.property_request.BuildingRequestDto;
import com.example.property.dto.property_response.BuildingResponseDto;
import com.example.property.entity.property.BuildingEntity;
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
public interface BuildingMapper {
    BuildingResponseDto toDTO(BuildingEntity entity);
    List<BuildingResponseDto> toDTOList(List<BuildingEntity> entities);

    List<BuildingEntity> fromDTOList(List<BuildingRequestDto> dtoList);

    BuildingEntity fromDTO(BuildingRequestDto dto);

    default Page<BuildingResponseDto> mapPageEntityToPageResponse(Page<BuildingEntity> buildingEntities) {
        List<BuildingResponseDto> dtoList = buildingEntities.getContent()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, buildingEntities.getPageable(), buildingEntities.getTotalElements());
    }
}