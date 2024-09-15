package com.example.property.mapper.property;

import com.example.property.dto.property_request.CountryRequestDto;
import com.example.property.dto.property_response.CountryResponseDto;
import com.example.property.dto.property_response.TaskResponseDto;
import com.example.property.entity.property.CountryEntity;
import com.example.property.entity.property.TaskEntity;
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
public interface CountryMapper {
    CountryResponseDto tomainDTO(CountryEntity entity);

    List<CountryResponseDto> toDTOList(List<CountryEntity> entities);

    List<CountryEntity> fromDTOList(List<CountryRequestDto> dtoList);

    CountryEntity fromDTO(CountryRequestDto dto);

    default Page<CountryResponseDto> mapPageEntityToPageResponse(Page<CountryEntity> countryEntities) {
        List<CountryResponseDto> dtoList = countryEntities.getContent()
                .stream()
                .map(this::tomainDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, countryEntities.getPageable(), countryEntities.getTotalElements());
    }
}

