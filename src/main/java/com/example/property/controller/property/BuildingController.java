package com.example.property.controller.property;

import com.example.property.dto.filter.RequestDto;
import com.example.property.dto.property_request.BuildingRequestDto;
import com.example.property.dto.property_response.BuildingResponseDto;
import com.example.property.dto.property_response.PropertyResponseDto;
import com.example.property.entity.user.User;
import com.example.property.exception.MethodArgumentNotValidException;
import com.example.property.filter.FilterSpecification;
import com.example.property.handler.SuccessDetails;
import com.example.property.repository.user.UserRepository;
import com.example.property.service.property.BuildingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Tag(name = "Building")
public class BuildingController {

    private final BuildingService buildingService;
    private final FilterSpecification<User> userFilterSpecification;

    @PostMapping("/building")
    @Operation(description = "Save operation",
            summary = "This is the summary for save building data",
            responses = {
                    @ApiResponse(description = "Success",responseCode = "200"),
                    @ApiResponse(description = "Unauthorized: Invalid Token",responseCode = "403"),
                    @ApiResponse(description = "Unauthorized: Invalid Token",responseCode = "401")
            })
    public ResponseEntity<SuccessDetails<String>> saveBuilding(@RequestBody @Valid BuildingRequestDto buildingRequestDto) throws MethodArgumentNotValidException {
        buildingService.saveBuilding(buildingRequestDto);
        return ResponseEntity.ok(new SuccessDetails<>("Building save Successfully!", HttpStatus.OK.value(),true));
    }

    @GetMapping("/building")
    @Operation(summary = "Find All Buildings")
    ResponseEntity<SuccessDetails<Page<BuildingResponseDto>>> findAllBuildings(@RequestParam(defaultValue = "0") int pageNumber,
                                                                               @RequestParam(defaultValue = "10")int pageSize,
                                                                               @RequestBody RequestDto requestDto){
        Specification<User> userSpecification = userFilterSpecification.getSearchSpecification(requestDto.getSearchRequestDto(),requestDto.getGlobalOperator());
        return ResponseEntity.ok(new SuccessDetails<>(buildingService.findAllBuildings(pageNumber,pageSize, (RequestDto) userSpecification),HttpStatus.OK.value(),true));
    }

    @GetMapping("/building/{buildingId}")
    @Operation(summary = "Find Building by id")
    ResponseEntity<SuccessDetails<BuildingResponseDto>> findBuildingById(@PathVariable("buildingId") Long buildingId) throws MethodArgumentNotValidException {
        return ResponseEntity.ok(new SuccessDetails<>(buildingService.findBuildingById(buildingId),HttpStatus.OK.value(),true));
    }

    @DeleteMapping("/building/{buildingId}")
    @Operation(summary = "Delete Building by id")
    ResponseEntity<SuccessDetails<String>> deleteBuildingById(@PathVariable("buildingId") Long buildingId) throws MethodArgumentNotValidException {
        buildingService.deleteBuildingById(buildingId);
       return ResponseEntity.ok(new SuccessDetails<>("Building delete Successfully",HttpStatus.OK.value(),true));
    }

    @PutMapping("/building")
    @Operation(summary = "Update Building")
    ResponseEntity<SuccessDetails<String>> updateBuilding(@RequestBody BuildingRequestDto buildingRequestDto) throws MethodArgumentNotValidException {
        buildingService.updateBuilding(buildingRequestDto);
        return ResponseEntity.ok(new SuccessDetails<>("Building update Successfully!",HttpStatus.OK.value(),true));
    }

    @PostMapping("/activate/building")
    @Operation(summary = "Building activated")
    public ResponseEntity<SuccessDetails<String>> activateBuilding(@RequestBody List<Long> buildingId) throws MethodArgumentNotValidException {
        buildingService.activateBuilding(buildingId);
        return ResponseEntity.ok(new SuccessDetails<>("Building activated Successfully!",HttpStatus.OK.value(),true));
    }

    @PostMapping("/deactivate/building")
    @Operation(summary = "Building deactivated")
    public ResponseEntity<SuccessDetails<String>> deactivateBuilding(@RequestBody List<Long> buildingId) throws MethodArgumentNotValidException {
        buildingService.deactivateBuilding(buildingId);
        return ResponseEntity.ok(new SuccessDetails<>("Building deactivated Successfully!",HttpStatus.OK.value(),true));
    }
    @PostMapping("/activate/building/responsible")
    @Operation(summary = "Building Responsible Person activated")
    public ResponseEntity<SuccessDetails<String>> activateResponsiblePerson(@RequestBody List<Long> respPersonId) throws MethodArgumentNotValidException {
        buildingService.activateResponsiblePerson(respPersonId);
        return ResponseEntity.ok(new SuccessDetails<>("Building Responsible Person activated Successfully!",HttpStatus.OK.value(),true));
    }
    @PostMapping("/deactivate/building/responsible")
    @Operation(summary = "Building Responsible Person deactivated")
    public ResponseEntity<SuccessDetails<String>> deactivateResponsiblePerson(@RequestBody List<Long> respPersonId) throws MethodArgumentNotValidException {
        buildingService.deactivateResponsiblePerson(respPersonId);
        return ResponseEntity.ok(new SuccessDetails<>("Building Responsible Person deactivated Successfully!",HttpStatus.OK.value(),true));
    }

    @PostMapping("/specification/building")
    @Operation(description = "Specification and filtering into building.")
    ResponseEntity<SuccessDetails<Page<BuildingResponseDto>>> getBuildingPage(@RequestBody RequestDto requestDto){
        return ResponseEntity.ok(new SuccessDetails<>(buildingService.getSpecification(requestDto),HttpStatus.OK.value(),true));
    }
}
