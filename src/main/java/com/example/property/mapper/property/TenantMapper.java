package com.example.property.mapper.property;

import com.example.property.dto.property_request.TenantRequestDto;
import com.example.property.dto.property_response.TenantResponseDto;
import com.example.property.entity.property.TenantEntity;
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
public interface TenantMapper {
    TenantResponseDto toDTO(TenantEntity entity);
    List<TenantResponseDto> toDTOList(List<TenantEntity> entities);

    List<TenantEntity> fromDTOList(List<TenantRequestDto> dtoList);

    TenantEntity fromDTO(TenantRequestDto dto);

    default Page<TenantResponseDto> mapPageEntityToPageResponse(Page<TenantEntity> tenantEntities) {
        List<TenantResponseDto> dtoList = tenantEntities.getContent()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, tenantEntities.getPageable(), tenantEntities.getTotalElements());
    }


}
