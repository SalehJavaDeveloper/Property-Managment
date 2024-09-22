package com.example.property.controller.property;

import com.example.property.dto.filter.RequestDto;
import com.example.property.dto.property_request.UnitRequestDto;
import com.example.property.dto.property_response.UnitResponseDto;
import com.example.property.exception.MethodArgumentNotValidException;
import com.example.property.handler.SuccessDetails;
import com.example.property.service.property.UnitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Tag(name = "Unit")
public class UnitController {

    private final UnitService unitService;

    @PostMapping("/unit")
    @Operation(description = "Save Unit operation",
            summary = "This is the summary for save unit data",
            responses = {
                    @ApiResponse(description = "Success",responseCode = "200"),
                    @ApiResponse(description = "Unauthorized: Invalid Token",responseCode = "403")
            })
    public ResponseEntity<SuccessDetails<String>> saveUnit(@RequestBody @Valid UnitRequestDto unitRequestDto) throws MethodArgumentNotValidException {
        unitService.saveUnit(unitRequestDto);
        return ResponseEntity.ok(new SuccessDetails<>("Unit save Successfully!", HttpStatus.OK.value(),true));
    }

    @PostMapping("/specification/unit")
    @Operation(description = "Specification and filtering into unit.")
    ResponseEntity<SuccessDetails<Page<UnitResponseDto>>> getUnitPage(@RequestBody RequestDto requestDto){
        return ResponseEntity.ok(new SuccessDetails<>(unitService.getSpecification(requestDto),HttpStatus.OK.value(),true));
    }

    @GetMapping("/unit")
    @PreAuthorize("hasAuthority('Pr_Update')")
    @Operation(summary = "Find All Units")
    ResponseEntity<SuccessDetails<Page<UnitResponseDto>>> findAllUnits(@RequestParam(defaultValue = "0") int pageNumber,
                                                                       @RequestParam(defaultValue = "10")int pageSize){
        return ResponseEntity.ok(new SuccessDetails<>(unitService.findAllUnits(pageNumber, pageSize),HttpStatus.OK.value(),true));
    }

    @GetMapping("/unit/{unitId}")
    @Operation(summary = "Find Unit by id")
    ResponseEntity<SuccessDetails<UnitResponseDto>> findUnitById(@PathVariable("unitId") Long unitId) throws MethodArgumentNotValidException {
        return ResponseEntity.ok(new SuccessDetails<>(unitService.findUnitById(unitId),HttpStatus.OK.value(),true));
    }

    @DeleteMapping("/unit/{unitId}")
    @Operation(summary = "Delete Unit by id")
    ResponseEntity<SuccessDetails<String>> deleteUnitById(@PathVariable("unitId") Long unitId) throws MethodArgumentNotValidException {
        unitService.deleteUnitById(unitId);
        return ResponseEntity.ok(new SuccessDetails<>("Unit deleted Successfully",HttpStatus.OK.value(),true));
    }

    @PutMapping("unit")
    @Operation(summary = "Update Unit")
    ResponseEntity<SuccessDetails<String>> updateUnit(@RequestBody UnitRequestDto unitRequestDto){
        unitService.updateUnit(unitRequestDto);
      return ResponseEntity.ok(new SuccessDetails<>("Unit update Successfully!",HttpStatus.OK.value(),true));
    }

    @PostMapping("/activate/unit")
    @Operation(summary = "Unit activated")
    public ResponseEntity<SuccessDetails<String>> activateUnit(@RequestBody List<Long> unitId){
        unitService.activateUnit(unitId);
        return ResponseEntity.ok(new SuccessDetails<>("Unit activated Successfully!",HttpStatus.OK.value(),true));
    }

    @PostMapping("/deactivate/unit")
    @Operation(summary = "Property deactivated")
    public ResponseEntity<SuccessDetails<String>> deactivateUnit(@RequestBody List<Long> unitId){
        unitService.deactivateUnit(unitId);
        return ResponseEntity.ok(new SuccessDetails<>("Unit deactivated Successfully!",HttpStatus.OK.value(),true));
    }
}
