package com.example.property.controller.role;

import com.example.property.dto.role_request.CompanyRolesRequestDto;
import com.example.property.dto.role_response.CompanyRolesResponseDto;
import com.example.property.handler.SuccessDetails;
import com.example.property.service.role.CompanyRolesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Company Roles")
public class CompanyRolesController {

    private final CompanyRolesService companyRolesService;

    @PostMapping("/company-roles")
    @Operation(description = "Save Company Roles operation",
            summary = "This is the summary for save Company Role data",
            responses = {
                    @ApiResponse(description = "Success",responseCode = "200"),
                    @ApiResponse(description = "Unauthorized: Invalid Token",responseCode = "403")
            })
    ResponseEntity<SuccessDetails<String>> saveCompanyRoles(@RequestBody List<CompanyRolesRequestDto> companyRolesRequestDto){
        companyRolesService.saveCompanyRoles(companyRolesRequestDto);
        return ResponseEntity.ok(new SuccessDetails<>("Company role save Successfully!", HttpStatus.OK.value(), true));
    }

    @GetMapping("/company-roles/{roleId}")
    @Operation(summary = "Find company roles by id")
    ResponseEntity<SuccessDetails<CompanyRolesResponseDto>> getCompanyRoleById(@PathVariable("roleId") Long roleId){
        return ResponseEntity.ok(new SuccessDetails<>(companyRolesService.findCompanyRolesById(roleId),HttpStatus.OK.value(), true));
    }

    @GetMapping("/company-roles")
    @Operation(summary = "Find all companyRoles")
    ResponseEntity<SuccessDetails<List<CompanyRolesResponseDto>>> findAllCompanyRoles(){
        return ResponseEntity.ok(new SuccessDetails<>(companyRolesService.findAllCompanyRoles(),HttpStatus.OK.value(), true));
    }

    @GetMapping("/commpany-role/{roleName}")
    @Operation(summary = "Find company roles by role name")
    ResponseEntity<SuccessDetails<List<CompanyRolesResponseDto>>> getCompanyRolesByName(@PathVariable("roleName") String roleName){
        return ResponseEntity.ok(new SuccessDetails<>(companyRolesService.findCompanyRolesByName(roleName),HttpStatus.OK.value(),true));
    }
}
