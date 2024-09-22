package com.example.property.service.user;

import com.example.property.dto.user.AuthenticationRequest;
import com.example.property.dto.user.AuthenticationResponse;
import com.example.property.dto.user.RegisterRequest;
import com.example.property.exception.AuthenticationException;
import com.example.property.exception.MethodArgumentNotValidException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthenticationService {

    AuthenticationResponse register(RegisterRequest request) throws MethodArgumentNotValidException;

    AuthenticationResponse authenticate(AuthenticationRequest request) throws AuthenticationException;


    AuthenticationResponse refreshToken(HttpServletRequest request,
                                        HttpServletResponse response) throws AuthenticationException;
}
