package com.example.property.service.role;

import com.example.property.dto.role_request.CompanyRequestDto;
import com.example.property.dto.role_request.CompanyRolesRequestDto;
import com.example.property.dto.role_response.CompanyRolesResponseDto;

import java.util.List;

public interface CompanyRolesService {

    void saveCompanyRoles(CompanyRolesRequestDto companyRolesRequestDto);

    CompanyRolesResponseDto findCompanyRolesById(Long id);

    List<CompanyRolesResponseDto> findAllCompanyRoles();

    List<CompanyRolesResponseDto> findCompanyRolesByName(String companyRolesName);
}
