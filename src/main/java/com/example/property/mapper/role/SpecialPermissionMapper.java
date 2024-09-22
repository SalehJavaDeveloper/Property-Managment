package com.example.property.mapper.role;

import com.example.property.dto.role_request.UserSpecialPermissionRequest;
import com.example.property.entity.companyRoles.UserSpecialPermissions;
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
public interface SpecialPermissionMapper {

    List<UserSpecialPermissions> fromDTOList(List<UserSpecialPermissionRequest> dtoList);

    UserSpecialPermissions fromDTO(UserSpecialPermissionRequest dto);
}
