package com.example.property.controller.user;

import ch.qos.logback.core.model.Model;
import com.example.property.dto.user.*;
import com.example.property.entity.user.User;
import com.example.property.exception.MethodArgumentNotValidException;
import com.example.property.handler.SuccessDetails;
import com.example.property.service.user.AuthenticationService;
import com.example.property.service.user.UserService;
import com.example.property.util.Utility;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.csrf.InvalidCsrfTokenException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@Tag(name = "UserCrudOperation")
public class UserOperationController {

    private final UserService userService;

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    @Operation(description = "Register operation",
            summary = "This is the summary for users registry simply",
            responses = {
                    @ApiResponse(description = "Success",responseCode = "200"),
                    @ApiResponse(description = "Unauthorized: Invalid Token",responseCode = "403")
            })
    public ResponseEntity<SuccessDetails<AuthenticationResponse>> register(@RequestBody RegisterRequest request) throws MethodArgumentNotValidException {
        return ResponseEntity.ok(new SuccessDetails<>(authenticationService.register(request), HttpStatus.OK.value(),true));
    }

    @PostMapping("/forgot_password")
    @Operation(summary = "Forgot user password")
    ResponseEntity<SuccessDetails<String>> processForgotPassword(HttpServletRequest request, @RequestParam String email) {

        String result = userService.updateResetPasswordToken(email, request);
        return ResponseEntity.ok(new SuccessDetails<>(result,HttpStatus.OK.value(), true));
    }

    @PostMapping("/reset_password")
    @Operation(summary = "Process reset password")
    ResponseEntity<SuccessDetails<User>> processResetPassword(
            @RequestParam String token,
            @RequestParam String resetRequest
    ) {
        User user = userService.getByResetPasswordToken(token);
//        System.out.println("!!!!!!!!BURDAYAAAM"+user);


        User user1 = userService.updatePassword(user, resetRequest);

        return ResponseEntity.ok(new SuccessDetails<>(user1, HttpStatus.OK.value(), true));
    }





}
