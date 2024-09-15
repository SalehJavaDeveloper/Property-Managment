package com.example.property.mapper.role;

import com.example.property.dto.role_request.CompanyRequestDto;
import com.example.property.dto.role_request.CompanyRolesRequestDto;
import com.example.property.dto.role_response.CompanyResponseDto;
import com.example.property.dto.role_response.CompanyRolesResponseDto;
import com.example.property.entity.companyRoles.Company;
import com.example.property.entity.companyRoles.CompanyRoles;
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
public interface CompanyRolesMapper {
    CompanyRolesResponseDto toDTO(CompanyRoles entity);
    List<CompanyRolesResponseDto> toDTOList(List<CompanyRoles> entities);
    List<CompanyRoles> fromDTOList(List<CompanyRolesRequestDto> dtoList);

    CompanyRoles fromDTO(CompanyRolesRequestDto dto);
}
