package com.example.property.dto.role_request;

import com.example.property.enumuration.Permissions;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CompanyRolesRequestDto {

    private Long id;

    private String companyRoleName;

    @Enumerated(EnumType.STRING)
    private Permissions permissions;

    private Long company_id;
}
