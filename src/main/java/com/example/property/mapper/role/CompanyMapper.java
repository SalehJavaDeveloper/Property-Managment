package com.example.property.mapper.role;

import com.example.property.dto.role_request.CompanyRequestDto;
import com.example.property.dto.role_response.CompanyResponseDto;
import com.example.property.entity.companyRoles.Company;
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
public interface CompanyMapper {
    CompanyResponseDto toDTO(Company entity);
    List<CompanyResponseDto> toDTOList(List<Company> entities);
    List<Company> fromDTOList(List<CompanyRequestDto> dtoList);

    Company fromDTO(CompanyRequestDto dto);
}
