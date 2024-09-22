package com.example.property.controller.property;


import com.example.property.dto.property_request.CityRequestDto;
import com.example.property.dto.property_response.CityResponseDto;
import com.example.property.exception.MethodArgumentNotValidException;
import com.example.property.handler.SuccessDetails;
import com.example.property.service.property.CityService;
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


@Controller
@RequiredArgsConstructor
@Tag(name = "City")
public class CityController {

    private final CityService cityService;


    @Operation(description = "Save operation",
            summary = "This is the summary for save city data",
            responses = {
                    @ApiResponse(description = "Success",responseCode = "200"),
                    @ApiResponse(description = "Unauthorized: Invalid Token",responseCode = "403")
            })
    @PostMapping("/city")
    public ResponseEntity<SuccessDetails<String>> saveCity(@RequestBody @Valid CityRequestDto cityDto) throws MethodArgumentNotValidException {

        cityService.saveCity(cityDto);
        return ResponseEntity.ok(new SuccessDetails<>("City save Successfully!", HttpStatus.OK.value(),true));
    }


    @GetMapping("/city")
    @Operation(summary = "Find all cities")
    public ResponseEntity<SuccessDetails<Page<CityResponseDto>>> findAllCity(@RequestParam(defaultValue = "0") int pageNumber,
                                                                             @RequestParam(defaultValue = "10")int pageSize){
        return ResponseEntity.ok(new SuccessDetails<>(cityService.findAllCity(pageNumber, pageSize), HttpStatus.OK.value(),true));
    }


    @GetMapping("/city/{id}")
    @Operation(summary = "Find City by id")
    public ResponseEntity<SuccessDetails<CityResponseDto>> getCityById(@PathVariable("id") Long id) throws MethodArgumentNotValidException {
        return ResponseEntity.ok(new SuccessDetails<>(cityService.findCityById(id),HttpStatus.OK.value(),true));
    }

    @DeleteMapping("/city/{id}")
    @Operation(summary = "Delete City by id")
    public ResponseEntity<SuccessDetails<String>> deleteCityById(@PathVariable("id") Long id) throws MethodArgumentNotValidException {
        cityService.deleteCityById(id);
        return ResponseEntity.ok(new SuccessDetails<>("City deleted Successfully!",HttpStatus.OK.value(),true));
    }

    @PutMapping("/city")
    @Operation(summary = "Update City")
    public  ResponseEntity<SuccessDetails<String>> updateCity(@RequestBody CityRequestDto cityDto) throws MethodArgumentNotValidException {
        cityService.updateCity(cityDto);
        return ResponseEntity.ok(new SuccessDetails<>("City update Successfully!",HttpStatus.OK.value(),true));
    }
}
