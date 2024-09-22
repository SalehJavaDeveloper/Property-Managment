package com.example.property.mapper.property;

import com.example.property.dto.property_request.MediaFileRequestDto;
import com.example.property.dto.property_request.TaskRequestDto;
import com.example.property.dto.property_request.TaskUpdateRequestDto;
import com.example.property.dto.property_response.MediaFileResponseDto;
import com.example.property.dto.property_response.TaskResponseDto;
import com.example.property.entity.property.MediaFileEntity;
import com.example.property.entity.property.TaskEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
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
public interface MediaFileMapper {
    MediaFileEntity mapRequestDtoToEntity(MediaFileRequestDto requestDto);

    List<MediaFileResponseDto> mapListEntityToListResponseDto(List<MediaFileEntity> mediaFileEntities);

    MediaFileResponseDto mapEntityToResponseDto(MediaFileEntity mediaFileEntity);

    void update(@MappingTarget MediaFileEntity mediaFileEntity, MediaFileRequestDto requestDto);
}
