package com.example.property.dto.role_request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CompanyRequestDto {

    private Long id;

    private String companyName;
}
