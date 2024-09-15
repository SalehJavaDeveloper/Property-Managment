package com.example.property.controller.property;


import com.example.property.dto.property_request.DistrictRequestDto;
import com.example.property.dto.property_response.DistrictResponseDto;
import com.example.property.exception.MethodArgumentNotValidException;
import com.example.property.handler.SuccessDetails;
import com.example.property.service.property.DistrictService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
@Tag(name = "District")
public class DistrictController {

    private final DistrictService districtService;

    @PostMapping("/district")
    @Operation(description = "Save operation",
            summary = "This is the summary for save district data",
            responses = {
                    @ApiResponse(description = "Success",responseCode = "200"),
                    @ApiResponse(description = "Unauthorized: Invalid Token",responseCode = "403")
            })
    public ResponseEntity<SuccessDetails<String>> saveDistrict(@RequestBody @Valid DistrictRequestDto districtDto) throws MethodArgumentNotValidException {
        districtService.saveDistrict(districtDto);
        return ResponseEntity.ok(new SuccessDetails<>("District save Successfully", HttpStatus.OK.value(),true));
    }


    @GetMapping("/district")
    @Operation(summary = "Find all districts")
    public ResponseEntity<SuccessDetails<Page<DistrictResponseDto>>> findAllDistricts(@RequestParam(defaultValue = "0") int pageNumber,
                                                                                      @RequestParam(defaultValue = "10")int pageSize){
        return ResponseEntity.ok(new SuccessDetails<>(districtService.findAllDistrict(pageNumber, pageSize), HttpStatus.OK.value(),true));
    }


    @GetMapping("/district/{id}")
    @Operation(summary = "Find District by id")
    public ResponseEntity<SuccessDetails<DistrictResponseDto>> getDistrictById(@PathVariable("id") Long id) throws MethodArgumentNotValidException {
        return ResponseEntity.ok(new SuccessDetails<>(districtService.findDistrictById(id),HttpStatus.OK.value(),true));
    }

    @DeleteMapping("/district/{id}")
    @Operation(summary = "Delete District by id")
    public ResponseEntity<SuccessDetails<String>> deleteDistrictById(@PathVariable("id") Long id) throws MethodArgumentNotValidException {
        districtService.deleteDistrictById(id);
        return ResponseEntity.ok(new SuccessDetails<>("District deleted Successfully!",HttpStatus.OK.value(),true));
    }

    @PutMapping("/district")
    @Operation(summary = "Update District")
    public  ResponseEntity<SuccessDetails<String>> updateDistrict(@RequestBody DistrictRequestDto districtDto) throws MethodArgumentNotValidException {
        districtService.updateDistrict(districtDto);
        return ResponseEntity.ok(new SuccessDetails<>("District update Successfully!",HttpStatus.OK.value(),true));
    }

}
