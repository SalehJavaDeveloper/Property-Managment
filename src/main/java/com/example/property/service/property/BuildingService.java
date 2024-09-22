package com.example.property.service.property;

import com.example.property.dto.filter.RequestDto;
import com.example.property.dto.property_request.BuildingRequestDto;
import com.example.property.dto.property_response.BuildingResponseDto;
import com.example.property.dto.property_response.PropertyResponseDto;
import com.example.property.exception.MethodArgumentNotValidException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BuildingService {

    void saveBuilding(BuildingRequestDto buildingRequestDto) throws  MethodArgumentNotValidException;

    Page<BuildingResponseDto> findAllBuildings(int pageNumber, int pageSize, RequestDto requestDto);

    Page<BuildingResponseDto> getSpecification(RequestDto requestDto);

    BuildingResponseDto findBuildingById(Long id) throws MethodArgumentNotValidException;

    void deleteBuildingById(Long id) throws MethodArgumentNotValidException;

    void activateBuilding(List<Long> id) throws MethodArgumentNotValidException;
    void deactivateBuilding(List<Long> id) throws MethodArgumentNotValidException;
    void activateResponsiblePerson(List<Long> id) throws  MethodArgumentNotValidException;

    void deactivateResponsiblePerson(List<Long> id) throws  MethodArgumentNotValidException;

    void updateBuilding(BuildingRequestDto buildingRequestDto) throws MethodArgumentNotValidException;
}
