package com.example.property.mapper.property;

import com.example.property.dto.property_request.VillageRequestDto;
import com.example.property.dto.property_response.TenantResponseDto;
import com.example.property.dto.property_response.VillageResponseDto;
import com.example.property.entity.property.TenantEntity;
import com.example.property.entity.property.VillageEntity;
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
public interface VillageMapper {

    VillageResponseDto toDTO(VillageEntity entity);
    List<VillageResponseDto> toDTOList(List<VillageEntity> entities);

    List<VillageEntity> fromDTOList(List<VillageRequestDto> dtoList);

    VillageEntity fromDTO(VillageRequestDto dto);

    default Page<VillageResponseDto> mapPageEntityToPageResponse(Page<VillageEntity> villageEntities) {
        List<VillageResponseDto> dtoList = villageEntities.getContent()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, villageEntities.getPageable(), villageEntities.getTotalElements());
    }
}
