package com.example.property.mapper.property;

import com.example.property.dto.property_request.UnitRequestDto;
import com.example.property.dto.property_response.TenantResponseDto;
import com.example.property.dto.property_response.UnitResponseDto;
import com.example.property.entity.property.TenantEntity;
import com.example.property.entity.property.UnitEntity;
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
public interface UnitMapper {
    UnitResponseDto toDTO(UnitEntity entity);
    List<UnitResponseDto> toDTOList(List<UnitEntity> entities);

    List<UnitEntity> fromDTOList(List<UnitRequestDto> dtoList);

    UnitEntity fromDTO(UnitRequestDto dto);

    default Page<UnitResponseDto> mapPageEntityToPageResponse(Page<UnitEntity> unitEntities) {
        List<UnitResponseDto> dtoList = unitEntities.getContent()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, unitEntities.getPageable(), unitEntities.getTotalElements());
    }
}
