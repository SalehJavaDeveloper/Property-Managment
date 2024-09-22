package com.example.property.mapper.role;

import com.example.property.dto.role_request.PermissionRequestDto;
import com.example.property.dto.role_response.PermissionResponseDto;
import com.example.property.entity.companyRoles.Permission;
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
public interface PermissionMapper {
    PermissionResponseDto toDTO(Permission entity);
    List<PermissionResponseDto> toDTOList(List<Permission> entities);
    List<Permission> fromDTOList(List<PermissionRequestDto> dtoList);

    Permission fromDTO(PermissionRequestDto dto);
}
