package com.example.property.controller.role;

import com.example.property.dto.role_request.UserSpecialPermissionRequest;
import com.example.property.dto.user.AuthenticationResponse;
import com.example.property.enumuration.Permissions;
import com.example.property.handler.SuccessDetails;
import com.example.property.service.role.SpecialPermissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Tag(name = "SpecialPermission")
public class SpecialPermissionController {

    private final SpecialPermissionService specialPermissionService;

    @PostMapping("/save/special/permission")
    @Operation(description = "Save Special Permission operation",
            summary = "This is the summary for save Special permission data",
            responses = {
                    @ApiResponse(description = "Success",responseCode = "200"),
                    @ApiResponse(description = "Unauthorized: Invalid Token",responseCode = "403")
            })
    public ResponseEntity<SuccessDetails<AuthenticationResponse>>  saveSpecialPermissions(@RequestBody List<UserSpecialPermissionRequest> userSpecialPermissionRequest){
        return  ResponseEntity.ok(new SuccessDetails<>(specialPermissionService.saveSpecialPermission(userSpecialPermissionRequest), HttpStatus.OK.value(),true));
    }

    @DeleteMapping("/deactivate/special/permission")
    @Operation(description = "Deactivate Special Permission operation",
            summary = "This is the summary for deactivate Special permission data",
            responses = {
                    @ApiResponse(description = "Success",responseCode = "200"),
                    @ApiResponse(description = "Unauthorized: Invalid Token",responseCode = "403")
            })
    public ResponseEntity<SuccessDetails<String>> deactivateSpecialPermission(@RequestBody Long role_id, @RequestBody Permissions permissions){
        specialPermissionService.deactivatePermission(role_id,permissions);
        return ResponseEntity.ok(new SuccessDetails<>("Permission deactivate Successfully!", HttpStatus.OK.value(),true));
    }

}
