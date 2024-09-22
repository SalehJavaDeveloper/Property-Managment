package com.example.property.service.user;

import com.example.property.dto.filter.RequestDto;
import com.example.property.dto.user_request.UserRequestDto;
import com.example.property.dto.user_response.UserResponseDto;
import com.example.property.entity.user.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;

public interface UserService {
    void updateUser(UserRequestDto userRequestDto);

    User findUserByUsername(String username);
    User findUserById(Long id);
    void deactivateUser(Long userId);
    Page<UserResponseDto> getSpecification(RequestDto requestDto);
    String updateResetPasswordToken(String email, HttpServletRequest request);

    User getByResetPasswordToken(String token);

    User updatePassword(User user, String resetRequest);

}
