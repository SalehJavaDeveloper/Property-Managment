package com.example.property.mapper.property;

import com.example.property.dto.property_request.DistrictRequestDto;
import com.example.property.dto.property_response.CountryResponseDto;
import com.example.property.dto.property_response.DistrictResponseDto;
import com.example.property.entity.property.CountryEntity;
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
public interface DistrictMapper {

    DistrictResponseDto toDTO(DistrictEntity entity);
    List<DistrictResponseDto> toDTOList(List<DistrictEntity> entities);

    List<DistrictEntity> fromDTOList(List<DistrictRequestDto> dtoList);

    DistrictEntity fromDTO(DistrictRequestDto dto);

    default Page<DistrictResponseDto> mapPageEntityToPageResponse(Page<DistrictEntity> districtEntities) {
        List<DistrictResponseDto> dtoList = districtEntities.getContent()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, districtEntities.getPageable(), districtEntities.getTotalElements());
    }
}
