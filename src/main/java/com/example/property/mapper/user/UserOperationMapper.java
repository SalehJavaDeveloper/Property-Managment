package com.example.property.mapper.user;

import com.example.property.dto.user_request.UserRequestDto;
import com.example.property.dto.user_response.UserResponseDto;
import com.example.property.entity.user.User;
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
public interface UserOperationMapper {

    UserResponseDto toDTO(User entity);

    List<UserResponseDto> toDTOList(List<User> entities);

    List<User> fromDTOList(List<UserRequestDto> dtoList);

    User fromDTO(UserRequestDto dto);

    default Page<UserResponseDto> mapPageEntityToPageResponse(Page<User> userPage) {
        List<UserResponseDto> dtoList = userPage.getContent()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, userPage.getPageable(), userPage.getTotalElements());
    }
}
