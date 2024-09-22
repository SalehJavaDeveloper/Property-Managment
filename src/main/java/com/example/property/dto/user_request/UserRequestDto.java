package com.example.property.dto.user_request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserRequestDto {

    private Long id;
    private String fullName;
    private String username;

    private String email;

    private String phoneNumber1;

    private String phoneNumber2;
    private Long companyId;

}
