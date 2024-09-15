package com.example.property.controller.user;

import com.example.property.dto.user.AuthenticationRequest;
import com.example.property.dto.user.AuthenticationResponse;
import com.example.property.exception.AuthenticationException;
import com.example.property.handler.SuccessDetails;
import com.example.property.service.user.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    @Operation(description = "Authenticate operation",
            summary = "This is the summary for users authenticate simply",
            responses = {
                    @ApiResponse(description = "Success",responseCode = "200"),
                    @ApiResponse(description = "Unauthorized: Invalid Data",responseCode = "403"),
                    @ApiResponse(description = "Unauthorized: Invalid Data",responseCode = "401")
            })
    public ResponseEntity<SuccessDetails<AuthenticationResponse>> authenticate(@RequestBody AuthenticationRequest request) throws AuthenticationException {
        return ResponseEntity.ok(new SuccessDetails<>(authenticationService.authenticate(request), HttpStatus.OK.value(),true));
    }

    @PostMapping("/refresh-token")
    @Operation(description = "Refresh token operation",
            summary = "This is the summary for refresh tokens",
            responses = {
                    @ApiResponse(description = "Success",responseCode = "200"),
                    @ApiResponse(description = "Unauthorized: Invalid Token",responseCode = "403"),
                    @ApiResponse(description = "Unauthorized: Invalid Token",responseCode = "401")
            })
    public ResponseEntity<SuccessDetails<AuthenticationResponse>> refreshToken(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
     return ResponseEntity.ok(new SuccessDetails<>(authenticationService.refreshToken(request,response),HttpStatus.OK.value(),true));
    }

}
