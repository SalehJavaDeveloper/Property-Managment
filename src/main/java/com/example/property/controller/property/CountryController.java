package com.example.property.controller.property;


import com.example.property.dto.property_request.CountryRequestDto;
import com.example.property.dto.property_response.CountryResponseDto;
import com.example.property.exception.MethodArgumentNotValidException;
import com.example.property.handler.SuccessDetails;
import com.example.property.service.property.CountryService;
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
@Tag(name = "Country")
public class CountryController {

    private final CountryService countryService;

    @PostMapping("/country")
    @Operation(description = "Save operation",
            summary = "This is the summary for save country data",
            responses = {
                    @ApiResponse(description = "Success",responseCode = "200"),
                    @ApiResponse(description = "Unauthorized: Invalid Token",responseCode = "403")
            })
    public ResponseEntity<SuccessDetails<String>> saveCountry(@RequestBody @Valid CountryRequestDto countryDto) throws MethodArgumentNotValidException {
        countryService.saveCountry(countryDto);
        return ResponseEntity.ok(new SuccessDetails<>("Country save Successfully", HttpStatus.OK.value(),true));
    }




    @GetMapping("/country")
    @Operation(summary = "Find all countries")
    public ResponseEntity<SuccessDetails<Page<CountryResponseDto>>> findAllCountry(@RequestParam(defaultValue = "0") int pageNumber,
                                                                                   @RequestParam(defaultValue = "10")int pageSize){
        return ResponseEntity.ok(new SuccessDetails<>(countryService.findAllCountry(pageNumber,pageSize), HttpStatus.OK.value(),true));
    }


    @GetMapping("/country/{id}")
    @Operation(summary = "Find country by id")
    public ResponseEntity<SuccessDetails<CountryResponseDto>> getCountryById(@PathVariable("id") Long id) throws MethodArgumentNotValidException {
        return ResponseEntity.ok(new SuccessDetails<>(countryService.findCountryById(id),HttpStatus.OK.value(),true));
    }

    @DeleteMapping("/country/{id}")
    @Operation(summary = "Delete country by id")
    public ResponseEntity<SuccessDetails<String>> deleteCountryById(@PathVariable("id") Long id) throws MethodArgumentNotValidException {
        countryService.deleteCountryById(id);
        return ResponseEntity.ok(new SuccessDetails<>("Country deleted Successfully!",HttpStatus.OK.value(),true));
    }

    @PutMapping("/country")
    @Operation(summary = "Update Country")
    public  ResponseEntity<SuccessDetails<String>> updateCountry(@RequestBody CountryRequestDto countryDto) throws MethodArgumentNotValidException {
        countryService.updateCountry(countryDto);
        return ResponseEntity.ok(new SuccessDetails<>("Country update Successfully!",HttpStatus.OK.value(),true));
    }
}
