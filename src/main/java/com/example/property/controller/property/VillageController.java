package com.example.property.controller.property;

import com.example.property.dto.property_request.VillageRequestDto;
import com.example.property.dto.property_response.VillageResponseDto;
import com.example.property.exception.MethodArgumentNotValidException;
import com.example.property.handler.SuccessDetails;
import com.example.property.service.property.VillageService;
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
@Tag(name = "Village")
public class VillageController {

    private final VillageService villageService;

    @PostMapping("/village")
    @Operation(description = "Save operation",
            summary = "This is the summary for save village data",
            responses = {
                    @ApiResponse(description = "Success",responseCode = "200"),
                    @ApiResponse(description = "Unauthorized: Invalid Token",responseCode = "403")
            })
    public ResponseEntity<SuccessDetails<String>> saveVillage(@RequestBody @Valid VillageRequestDto villageDto) throws MethodArgumentNotValidException {
        villageService.saveVillage(villageDto);
        return ResponseEntity.ok(new SuccessDetails<>("Village save Successfully!", HttpStatus.OK.value(),true));
    }

    @GetMapping("/village")
    @Operation(summary = "Find all Villages")
   @PreAuthorize("hasAuthority('Pr_Read')")
    public ResponseEntity<SuccessDetails<Page<VillageResponseDto>>> findAllVillages(@RequestParam(defaultValue = "0") int pageNumber,
                                                                                    @RequestParam(defaultValue = "10")int pageSize){
        return ResponseEntity.ok(new SuccessDetails<>(villageService.findAllVillage(pageNumber, pageSize), HttpStatus.OK.value(),true));
    }


    @GetMapping("/village/{id}")
    @Operation(summary = "Find Village by id")
    public ResponseEntity<SuccessDetails<VillageResponseDto>> getVillageById(@PathVariable("id") Long id) throws MethodArgumentNotValidException {
        return ResponseEntity.ok(new SuccessDetails<>(villageService.findVillageById(id),HttpStatus.OK.value(),true));
    }

    @DeleteMapping("/village/{id}")
    @Operation(summary = "Delete Village by id")
    public ResponseEntity<SuccessDetails<String>> deleteDistrictById(@PathVariable("id") Long id) throws MethodArgumentNotValidException {
        villageService.deleteVillageById(id);
        return ResponseEntity.ok(new SuccessDetails<>("Village deleted Successfully!",HttpStatus.OK.value(),true));
    }

    @PutMapping("/village")
    @Operation(summary = "Update District")
    public  ResponseEntity<SuccessDetails<String>> updateDistrict(@RequestBody VillageRequestDto villageDto) throws MethodArgumentNotValidException {
        villageService.updateVillage(villageDto);
        return ResponseEntity.ok(new SuccessDetails<>("Village update Successfully!",HttpStatus.OK.value(),true));
    }
}
