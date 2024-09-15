package com.example.property.service.user;

import com.example.property.dto.user.UserDto;
import com.example.property.dto.user.UserUpdateRequestDto;
import com.example.property.exception.MethodArgumentNotValidException;
import jakarta.mail.MessagingException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    Page<UserDto> findAllUsers(int pageNumber, int pageSize);

    UserDto findUserByUsername(String username) throws MethodArgumentNotValidException;

    UserDto findUserById(Long id) throws MethodArgumentNotValidException;


    void userUpdate(Long id, UserUpdateRequestDto userUpdateRequestDto);

    String forgotPassword(String email) throws MessagingException;
    String setPassword(String email,String newPassword);
}
