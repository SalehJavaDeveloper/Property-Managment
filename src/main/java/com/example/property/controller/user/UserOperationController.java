package com.example.property.controller.user;

import com.example.property.dto.role_request.UserGrantedRequestDto;
import com.example.property.dto.user.AuthenticationResponse;
import com.example.property.dto.user.RegisterRequest;
import com.example.property.dto.user.UserDto;
import com.example.property.dto.user.UserUpdateRequestDto;
import com.example.property.exception.MethodArgumentNotValidException;
import com.example.property.handler.SuccessDetails;
import com.example.property.service.user.AuthenticationService;
import com.example.property.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/users")
    @Operation(summary = "Find all users")
    ResponseEntity<SuccessDetails<Page<UserDto>>> findAllUsers(@RequestParam(defaultValue = "0") int pageNumber,
                                                               @RequestParam(defaultValue = "10")int pageSize){
        return ResponseEntity.ok(new SuccessDetails<>(userService.findAllUsers(pageNumber, pageSize), HttpStatus.OK.value(),true));
    }

    @GetMapping("/user/username/{username}")
    @Operation(summary = "Find users by username")
    ResponseEntity<SuccessDetails<UserDto>> findUserByUsername(@PathVariable("username") String username) throws MethodArgumentNotValidException {
        return ResponseEntity.ok(new SuccessDetails<>(userService.findUserByUsername(username),HttpStatus.OK.value(),true));
    }

    @GetMapping("/user/{id}")
    @Operation(summary = "Find users by id")
    ResponseEntity<SuccessDetails<UserDto>> findUserById(@PathVariable("id") Long id) throws MethodArgumentNotValidException {
        return ResponseEntity.ok(new SuccessDetails<>(userService.findUserById(id),HttpStatus.OK.value(),true));
    }

    @PutMapping("/user/{id}")
    @Operation(summary = "Update user information")
    ResponseEntity<SuccessDetails<String>> updateUser(@PathVariable("id") Long id, @RequestBody UserUpdateRequestDto userUpdateRequestDto) {
        userService.userUpdate(id, userUpdateRequestDto);
        return ResponseEntity.ok(new SuccessDetails<>("User updated Succesfully!", HttpStatus.OK.value(), true));
    }

    @PutMapping("/forgot-password")
    @Operation(summary = "Forgot user password")
    ResponseEntity<SuccessDetails<String>> forgotPassword(@RequestParam String email) throws MessagingException {
        userService.forgotPassword(email);
        return ResponseEntity.ok(new SuccessDetails<>("User's password deleted Succesfully!",HttpStatus.OK.value(),true));
    }

    @PutMapping("/set-password")
    @Operation(summary = "Forgot user password")
    ResponseEntity<SuccessDetails<String>> setPassword(@RequestParam String email, @RequestHeader String newPassword) throws MessagingException {
        userService.setPassword(email,newPassword);
        return ResponseEntity.ok(new SuccessDetails<>("User's new password setted Succesfully!",HttpStatus.OK.value(),true));
    }
}
