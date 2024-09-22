package com.example.property.service.role;

import com.example.property.dto.role_request.UserGrantedRequestDto;
import com.example.property.dto.role_response.UserGrantedResponseDto;
import com.example.property.dto.user.AuthenticationResponse;

import java.util.List;

public interface UserGrantedRoleService {

    AuthenticationResponse saveUserGrantedRole(UserGrantedRequestDto userGrantedRequestDto);

    UserGrantedResponseDto findUserGrantedRoleById(Long id);

    List<UserGrantedResponseDto> findAll();
}
