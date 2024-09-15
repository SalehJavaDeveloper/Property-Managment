package com.example.property.controller.property;

import com.example.property.dto.filter.RequestDto;
import com.example.property.dto.property_request.TenantRequestDto;
import com.example.property.dto.property_response.TenantResponseDto;
import com.example.property.exception.MethodArgumentNotValidException;
import com.example.property.handler.SuccessDetails;
import com.example.property.service.property.TenantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Tag(name = "Tenant")
public class  TenantController {

    private final TenantService tenantService;

    @GetMapping("/tenants")
    @Operation(summary = "Find All Tenants")
    public ResponseEntity<SuccessDetails<Page<TenantResponseDto>>> findAllTenants(@RequestParam(defaultValue = "0") int pageNumber,
                                                                                  @RequestParam(defaultValue = "10")int pageSize){
        return ResponseEntity.ok(new SuccessDetails<>(tenantService.findAllTenant(pageNumber, pageSize), HttpStatus.OK.value(),true));
    }

    @GetMapping("/tenants/{id}")
    @Operation(summary = "Find Tenant by id")
    public ResponseEntity<SuccessDetails<TenantResponseDto>> getTenantById(@PathVariable("id") Long id) throws MethodArgumentNotValidException {
        return ResponseEntity.ok(new SuccessDetails<>(tenantService.findTenantById(id),HttpStatus.OK.value(),true));
    }

    @PostMapping("/tenants")
    @Operation(summary = "save Tenants")
    public ResponseEntity<SuccessDetails<String>> saveTenant(@RequestBody @Valid TenantRequestDto tenantRequestDto) throws MethodArgumentNotValidException {
        tenantService.saveTenant(tenantRequestDto);
        return ResponseEntity.ok(new SuccessDetails<>("Tenant save Successfully!",HttpStatus.OK.value(),true));
    }

    @DeleteMapping("/tenants/{id}")
    @Operation(summary = "Delete Tenants by id")
    public ResponseEntity<SuccessDetails<String>> deleteTenantById(@PathVariable("id") Long id) throws MethodArgumentNotValidException {
        tenantService.deleteTenantById(id);
        return ResponseEntity.ok(new SuccessDetails<>("Tenant deleted Successfully!",HttpStatus.OK.value(),true));
    }

    @PutMapping("/tenants")
    @Operation(summary = "Update Tenant")
    public  ResponseEntity<SuccessDetails<String>> updateTenant(@RequestBody TenantRequestDto tenantRequestDto) throws MethodArgumentNotValidException {
        tenantService.updateTenant(tenantRequestDto);
        return ResponseEntity.ok(new SuccessDetails<>("Tenant update Successfully!",HttpStatus.OK.value(),true));
    }

    @PostMapping("/activate/tenant")
    @Operation(summary = "Tenant activated")
    public ResponseEntity<SuccessDetails<String>> activateTenant(@RequestBody List<Long> tenantId){
        tenantService.activateTenant(tenantId);
        return ResponseEntity.ok(new SuccessDetails<>("Tenant activated Successfully!",HttpStatus.OK.value(),true));
    }

    @PostMapping("/deactivate/tenant")
    @Operation(summary = "Tenant deactivated")
    public ResponseEntity<SuccessDetails<String>> deactivateTenant(@RequestBody List<Long> tenantId){
        tenantService.deactivateTenant(tenantId);
        return ResponseEntity.ok(new SuccessDetails<>("Tenant deactivated Successfully!",HttpStatus.OK.value(),true));
    }

    @PostMapping("/specification/tenant")
    @Operation(description = "Specification and filtering into tenant.")
    ResponseEntity<SuccessDetails<Page<TenantResponseDto>>> getTenantPage(@RequestBody RequestDto requestDto){
        return ResponseEntity.ok(new SuccessDetails<>(tenantService.getSpecification(requestDto),HttpStatus.OK.value(),true));
    }
}
