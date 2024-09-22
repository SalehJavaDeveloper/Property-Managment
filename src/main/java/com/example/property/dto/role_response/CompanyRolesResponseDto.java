package com.example.property.dto.role_response;

import com.example.property.entity.companyRoles.Company;
import com.example.property.enumuration.Permissions;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CompanyRolesResponseDto {

    private Long id;

    private String companyRoleName;

    @Enumerated(EnumType.STRING)
    private Permissions permissions;

    private Company companyRoles;
}
