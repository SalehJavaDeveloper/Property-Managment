package com.example.property.service.property;

import com.example.property.dto.filter.RequestDto;
import com.example.property.dto.property_request.UnitRequestDto;
import com.example.property.dto.property_response.UnitResponseDto;
import com.example.property.exception.MethodArgumentNotValidException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UnitService {

    void saveUnit(UnitRequestDto unitRequestDto) throws MethodArgumentNotValidException;

    Page<UnitResponseDto> findAllUnits(int pageNumber, int pageSize);

    UnitResponseDto findUnitById(Long id) throws MethodArgumentNotValidException;

    void deleteUnitById(Long id) throws MethodArgumentNotValidException;

    void activateUnit(List<Long> id) throws MethodArgumentNotValidException;
    void deactivateUnit(List<Long> id) throws MethodArgumentNotValidException;

    Page<UnitResponseDto> getSpecification(RequestDto requestDto);

    void updateUnit(UnitRequestDto unitRequestDto);
}
