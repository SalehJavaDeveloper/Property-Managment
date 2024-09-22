package com.example.property.mapper.property;

import com.example.property.dto.property_request.TaskRequestDto;
import com.example.property.dto.property_response.TaskResponseDto;
import com.example.property.dto.property_request.TaskUpdateRequestDto;
import com.example.property.entity.property.TaskEntity;
import org.mapstruct.*;
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
public interface TaskMapper {

    TaskEntity mapRequestDtoToEntity(TaskRequestDto requestDto);

    List<TaskResponseDto> mapListEntityToListResponseDto(List<TaskEntity> taskEntities);

    TaskResponseDto mapEntityToResponseDto(TaskEntity taskEntity);


    default Page<TaskResponseDto> mapPageEntityToPageResponse(Page<TaskEntity> taskEntityPage) {
        List<TaskResponseDto> dtoList = taskEntityPage.getContent()
                .stream()
                .map(this::mapEntityToResponseDto)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, taskEntityPage.getPageable(), taskEntityPage.getTotalElements());
    }

    void update(@MappingTarget TaskEntity taskEntity, TaskUpdateRequestDto updateRequestDto);

}
