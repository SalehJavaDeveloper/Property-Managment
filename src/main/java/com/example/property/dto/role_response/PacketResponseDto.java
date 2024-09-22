package com.example.property.dto.role_response;

import com.example.property.entity.companyRoles.Company;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class PacketResponseDto {

    private Long id;
    private Company company;
}
