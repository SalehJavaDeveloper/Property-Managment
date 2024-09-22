package com.example.property.controller.property;


import com.example.property.dto.property_request.StreetRequestDto;
import com.example.property.dto.property_response.StreetResponseDto;
import com.example.property.exception.MethodArgumentNotValidException;
import com.example.property.handler.SuccessDetails;
import com.example.property.service.property.StreetService;
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
@Tag(name = "Street")
public class StreetController {

    private final StreetService streetService;

    @PostMapping("/street")
    @Operation(description = "Save operation",
            summary = "This is the summary for save street data",
            responses = {
                    @ApiResponse(description = "Success",responseCode = "200"),
                    @ApiResponse(description = "Unauthorized: Invalid Token",responseCode = "403")
            })
    public ResponseEntity<SuccessDetails<String>> saveStreet(@RequestBody @Valid StreetRequestDto streetDto) throws MethodArgumentNotValidException {
        streetService.saveStreet(streetDto);
        return ResponseEntity.ok(new SuccessDetails<>("Street save Successfully", HttpStatus.OK.value(),true));
    }

    @GetMapping("/street")
    @Operation(summary = "Find all streets")
    public ResponseEntity<SuccessDetails<Page<StreetResponseDto>>> findAllStreets(@RequestParam(defaultValue = "0") int pageNumber,
                                                                                  @RequestParam(defaultValue = "10")int pageSize){
        return ResponseEntity.ok(new SuccessDetails<>(streetService.findAllStreet(pageNumber, pageSize), HttpStatus.OK.value(),true));
    }


    @GetMapping("/street/{id}")
    @Operation(summary = "Find Street by id")
    public ResponseEntity<SuccessDetails<StreetResponseDto>> getStreetById(@PathVariable("id") Long id) throws MethodArgumentNotValidException {
        return ResponseEntity.ok(new SuccessDetails<>(streetService.findStreetById(id),HttpStatus.OK.value(),true));
    }

    @DeleteMapping("/street/{id}")
    @Operation(summary = "Delete Street by id")
    public ResponseEntity<SuccessDetails<String>> deleteStreetById(@PathVariable("id") Long id) throws MethodArgumentNotValidException {
        streetService.deleteStreetById(id);
        return ResponseEntity.ok(new SuccessDetails<>("DStreet deleted Successfully!",HttpStatus.OK.value(),true));
    }

    @PutMapping("/street")
    @Operation(summary = "Update Street")
    public  ResponseEntity<SuccessDetails<String>> updateStreet(@RequestBody StreetRequestDto streetDto) throws MethodArgumentNotValidException {
        streetService.updateStreet(streetDto);
        return ResponseEntity.ok(new SuccessDetails<>("Street update Successfully!",HttpStatus.OK.value(),true));
    }
}
