package com.example.property.controller.role;

import com.example.property.dto.role_request.UserGrantedRequestDto;
import com.example.property.dto.role_response.UserGrantedResponseDto;
import com.example.property.handler.SuccessDetails;
import com.example.property.service.role.UserGrantedRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Tag(name = "User Granted Roles")
public class UserGrantedRolesController {

    private final UserGrantedRoleService userGrantedRoleService;

    @PostMapping("/user_role")
    @Operation(description = "Save User role operation",
            summary = "This is the summary for save user role data",
            responses = {
                    @ApiResponse(description = "Success",responseCode = "200"),
                    @ApiResponse(description = "Unauthorized: Invalid Token",responseCode = "403")
            })
    ResponseEntity<SuccessDetails<String>> saveUserGrantedRoles(@RequestBody UserGrantedRequestDto userGrantedRequestDto){
        userGrantedRoleService.saveUserGrantedRole(userGrantedRequestDto);
        return ResponseEntity.ok(new SuccessDetails<>("User Granted Role successfully assigned!", HttpStatus.OK.value(),true));
    }

    @GetMapping("/user-role/{roleId}")
    @Operation(summary = "Find user roles by id")
    ResponseEntity<SuccessDetails<UserGrantedResponseDto>> findUserGrantedRoleById(@PathVariable("roleId") Long roleId){
        return ResponseEntity.ok(new SuccessDetails<>(userGrantedRoleService.findUserGrantedRoleById(roleId),HttpStatus.OK.value(),true));
    }

    @GetMapping("/user-role")
    @Operation(summary = "Find all user roles")
    ResponseEntity<SuccessDetails<List<UserGrantedResponseDto>>> findAllUserGrantedRoles(){
        return ResponseEntity.ok(new SuccessDetails<>(userGrantedRoleService.findAll(),HttpStatus.OK.value(),true));
    }
}
