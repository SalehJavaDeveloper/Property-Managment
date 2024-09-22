package com.example.property.dto.user_response;

import com.example.property.entity.companyRoles.Company;
import com.example.property.entity.user.UserGrantedRoles;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class UserResponseDto {
    private Long id;

    private String fullName;
    private String username;

    private String email;

    private String phoneNumber1;

    private String phoneNumber2;
    private Company companyOfUsers;
    private List<UserGrantedRoles> userGrantedRoles;
}
