package com.example.property.controller.role;

import com.example.property.dto.role_request.CompanyRequestDto;
import com.example.property.handler.SuccessDetails;
import com.example.property.service.role.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
@Tag(name = "Company")
public class CompanyController {

    private final CompanyService companyService;
    @Operation(description = "Save Company operation",
            summary = "This is the summary for save Company data",
            responses = {
                    @ApiResponse(description = "Success",responseCode = "200"),
                    @ApiResponse(description = "Unauthorized: Invalid Token",responseCode = "403")
            })
    @PostMapping("/save/company")
    public ResponseEntity<SuccessDetails<String>> saveCompany(@RequestBody CompanyRequestDto companyRequestDto){
        companyService.saveCompany(companyRequestDto);
        return ResponseEntity.ok(new SuccessDetails<>("Company save SuccessDFully!", HttpStatus.OK.value(), true));
    }
}
