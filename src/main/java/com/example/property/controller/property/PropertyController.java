package com.example.property.controller.property;

import com.example.property.dto.filter.PageRequestDto;
import com.example.property.dto.filter.RequestDto;
import com.example.property.dto.property_request.PropertyRequestDto;
import com.example.property.dto.property_response.PropertyResponseDto;
import com.example.property.entity.property.PropertyEntity;
import com.example.property.entity.user.User;
import com.example.property.exception.MethodArgumentNotValidException;
import com.example.property.filter.FilterSpecification;
import com.example.property.handler.SuccessDetails;
import com.example.property.mapper.property.PropertyMapper;
import com.example.property.repository.property.PropertyRepository;
import com.example.property.service.property.PropertyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Tag(name = "Property")
public class PropertyController {

    private final PropertyService propertyService;

    @PostMapping("/property")
    @Operation(description = "Save operation",
            summary = "This is the summary for save property data",
            responses = {
                    @ApiResponse(description = "Success",responseCode = "200"),
                    @ApiResponse(description = "Unauthorized: Invalid Token",responseCode = "403")
            })
    public ResponseEntity<SuccessDetails<String>> saveProperty(@RequestBody @Valid PropertyRequestDto propertyRequestDto) throws MethodArgumentNotValidException {
        propertyService.saveProperty(propertyRequestDto);
        return ResponseEntity.ok(new SuccessDetails<>("Property Save Successfully!", HttpStatus.OK.value(), true));
    }

    @GetMapping("/property")
    @Operation(summary = "Find All Property")
    ResponseEntity<SuccessDetails<Page<PropertyResponseDto>>> findAllProperty(@RequestParam(defaultValue = "0") int pageNumber,
                                                                              @RequestParam(defaultValue = "10")int pageSize){
        return ResponseEntity.ok(new SuccessDetails<>(propertyService.findAllProperty(pageNumber, pageSize),200,true));
    }

    @GetMapping("/property/{propertyId}")
    @Operation(summary = "Find Property by id")
    ResponseEntity<SuccessDetails<PropertyResponseDto>> findPropertyById(@PathVariable("propertyId") Long propertyId) throws MethodArgumentNotValidException {
        return ResponseEntity.ok(new SuccessDetails<>(propertyService.findPropertyById(propertyId),HttpStatus.OK.value(),true));
    }

    @DeleteMapping("/property/{propertyId}")
    @Operation(summary = "Delete Property by id")
    ResponseEntity<SuccessDetails<String>> deletePropertyById(@PathVariable("propertyId") Long propertyId) throws MethodArgumentNotValidException {
        propertyService.deletePropertyById(propertyId);
        return ResponseEntity.ok(new SuccessDetails<>("Property deleted successfully!",HttpStatus.OK.value(),true));
    }

    @PutMapping("/property")
    @Operation(summary = "Update Property")
    public ResponseEntity<SuccessDetails<String>> updateProperty(@RequestBody PropertyRequestDto propertyRequestDto) throws MethodArgumentNotValidException {
        propertyService.updateProperty(propertyRequestDto);
        return ResponseEntity.ok(new SuccessDetails<>("Property update Successfully!",HttpStatus.OK.value(),true));
    }

    @PostMapping("/activate/property")
    @Operation(summary = "Property activated")
    public ResponseEntity<SuccessDetails<String>> activateProperty(@RequestBody List<Long> propertyId){
        propertyService.activateProperty(propertyId);
        return ResponseEntity.ok(new SuccessDetails<>("Property activated Successfully!",HttpStatus.OK.value(),true));
    }

    @PostMapping("/deactivate/property")
    @Operation(summary = "Property deactivated")
    public ResponseEntity<SuccessDetails<String>> deactivateProperty(@RequestBody List<Long> propertyId){
        propertyService.deactivateProperty(propertyId);
        return ResponseEntity.ok(new SuccessDetails<>("Property deactivated Successfully!",HttpStatus.OK.value(),true));
    }
    @PostMapping("/activate/responsible")
    @Operation(summary = "Property Responsible Person activated")
    public ResponseEntity<SuccessDetails<String>> activateResponsiblePerson(@RequestBody List<Long> respPersonId){
        propertyService.activateResponsiblePerson(respPersonId);
        return ResponseEntity.ok(new SuccessDetails<>("Property Responsible Person activated Successfully!",HttpStatus.OK.value(),true));
    }
    @PostMapping("/deactivate/responsible")
    @Operation(summary = "Property Responsible Person deactivated")
    public ResponseEntity<SuccessDetails<String>> deactivateResponsiblePerson(@RequestBody List<Long> respPersonId){
        propertyService.deactivateResponsiblePerson(respPersonId);
        return ResponseEntity.ok(new SuccessDetails<>("Property Responsible Person deactivated Successfully!",HttpStatus.OK.value(),true));
    }
    @PostMapping("/specification/property")
    @Operation(description = "Specification and filtering into property.")
    ResponseEntity<SuccessDetails<Page<PropertyResponseDto>>> getPropertyPage(@RequestBody RequestDto requestDto){
        return ResponseEntity.ok(new SuccessDetails<>(propertyService.getSpecification(requestDto),HttpStatus.OK.value(),true));
    }
}
