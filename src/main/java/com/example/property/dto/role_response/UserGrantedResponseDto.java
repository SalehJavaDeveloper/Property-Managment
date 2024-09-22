package com.example.property.dto.role_response;

import com.example.property.entity.user.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserGrantedResponseDto {

    private Long id;

    private String userRole;

    private User user;
}
