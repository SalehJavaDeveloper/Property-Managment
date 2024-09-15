package com.example.property.controller.role;

import com.example.property.dto.role_request.PermissionRequestDto;
import com.example.property.dto.role_response.PermissionResponseDto;
import com.example.property.handler.SuccessDetails;
import com.example.property.service.role.PermissionService;
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
@Tag(name = "Permissions")
public class PermissionController {

    private final PermissionService permissionService;

    @PostMapping("/permission")
    @Operation(description = "Save Permissions operation",
            summary = "This is the summary for save Permissions data",
            responses = {
                    @ApiResponse(description = "Success",responseCode = "200"),
                    @ApiResponse(description = "Unauthorized: Invalid Token",responseCode = "403")
            })
    public ResponseEntity<SuccessDetails<String>> savePermission(@RequestBody PermissionRequestDto permissionRequestDto){
        permissionService.savePermission(permissionRequestDto);
        return ResponseEntity.ok(new SuccessDetails<>("Permission save SuccessFully!", HttpStatus.OK.value(),true));
    }
    @GetMapping("/permission/{permissionId}")
    @Operation(summary = "Find Permissions by id")
    public ResponseEntity<SuccessDetails<PermissionResponseDto>> findPermissionById(@PathVariable("permissionId") Long permissionId){
       return ResponseEntity.ok(new SuccessDetails<>(permissionService.findPermissionsById(permissionId),HttpStatus.OK.value(), true));
    }

    @GetMapping("/permission")
    @Operation(summary = "Find all Permissions")
    public ResponseEntity<SuccessDetails<List<PermissionResponseDto>>> findAllPermissions(){
        return ResponseEntity.ok(new SuccessDetails<>(permissionService.findAllPermissions(),HttpStatus.OK.value(),true));
    }
}
