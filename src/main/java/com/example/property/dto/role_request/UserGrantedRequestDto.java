package com.example.property.dto.role_request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserGrantedRequestDto {

    private Long id;

    private String userRole;

    private Long user_id;
}
