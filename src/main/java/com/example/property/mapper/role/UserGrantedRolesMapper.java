package com.example.property.mapper.role;

import com.example.property.dto.role_request.PermissionRequestDto;
import com.example.property.dto.role_request.UserGrantedRequestDto;
import com.example.property.dto.role_response.PermissionResponseDto;
import com.example.property.dto.role_response.UserGrantedResponseDto;
import com.example.property.entity.companyRoles.Permission;
import com.example.property.entity.user.UserGrantedRoles;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface UserGrantedRolesMapper {
    UserGrantedResponseDto toDTO(UserGrantedRoles entity);
    List<UserGrantedResponseDto> toDTOList(List<UserGrantedRoles> entities);
    List<UserGrantedRoles> fromDTOList(List<UserGrantedRequestDto> dtoList);
    UserGrantedRoles fromDTO(UserGrantedRequestDto dto);
}
