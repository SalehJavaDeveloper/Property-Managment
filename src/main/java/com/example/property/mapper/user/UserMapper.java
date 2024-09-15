package com.example.property.mapper.user;

import com.example.property.dto.property_request.TaskUpdateRequestDto;
import com.example.property.dto.property_response.CityResponseDto;
import com.example.property.dto.user.UserDto;
import com.example.property.dto.user.UserUpdateRequestDto;
import com.example.property.entity.property.CityEntity;
import com.example.property.entity.property.TaskEntity;
import com.example.property.entity.user.User;
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
public interface UserMapper {
    UserDto toDTO(User entity);

    List<UserDto> toDTOList(List<User> entities);

    List<User> fromDTOList(List<UserDto> dtoList);

    User fromDTO(UserDto dto);

    default Page<UserDto> mapPageEntityToPageResponse(Page<User> users) {
        List<UserDto> dtoList = users.getContent()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, users.getPageable(), users.getTotalElements());
    }
    void update(@MappingTarget User user, UserUpdateRequestDto userUpdateRequestDto);
   // void update(@MappingTarget TaskEntity taskEntity, TaskUpdateRequestDto updateRequestDto);
}
